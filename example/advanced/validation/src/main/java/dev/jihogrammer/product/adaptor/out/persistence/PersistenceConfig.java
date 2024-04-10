package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public ProductPort productPort() {
        return new InMemoryProductAdaptor();
    }

}
