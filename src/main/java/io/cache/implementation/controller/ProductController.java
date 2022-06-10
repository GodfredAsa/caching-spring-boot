package io.cache.implementation.controller;

import io.cache.implementation.entity.Product;
import io.cache.implementation.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/vi")

public class ProductController {
    @Autowired
    private ProductRepository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProducts(){
        return repository.findAll();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    @Cacheable(value = "product-cache")
    @Transactional(readOnly = true)
    public Product getProductById(@PathVariable("id") Integer id){
        LOGGER.info("finding product by id " + id);
        return repository.getById(id);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    @CacheEvict(value = "product-cache")
    public void deleteProductById(@PathVariable("id") Integer id){
        LOGGER.info("deleting product by id " + id);
         repository.deleteById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product){
        Product createdProduct = repository.save(product);
        LOGGER.info("created product with id " + createdProduct.getId());

        return createdProduct;
    }

}
