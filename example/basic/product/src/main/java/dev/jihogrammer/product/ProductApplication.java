package dev.jihogrammer.product;

import dev.jihogrammer.web.core.WebCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication extends WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
