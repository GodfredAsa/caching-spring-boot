package io.cache.implementation.config;
import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCacheConfig {
    @Bean
    public Config cacheConfiguration(){

        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("product-cache")
                        .setTimeToLiveSeconds(3000)
                        .setEvictionConfig(new EvictionConfig().setMaxSizePolicy(MaxSizePolicy.PER_PARTITION)));
    }
}
