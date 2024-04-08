package dev.jihogrammer.product;

import dev.jihogrammer.product.adaptor.out.InMemoryProductRepository;
import dev.jihogrammer.product.port.out.Products;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

    @Bean
    public Products products() {
        return new InMemoryProductRepository();
    }

}
