package io.cache.implementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCachingApplication.class, args);
	}

}
