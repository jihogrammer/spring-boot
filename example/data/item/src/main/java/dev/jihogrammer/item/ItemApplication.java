package dev.jihogrammer.item;

import dev.jihogrammer.item.application.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ApplicationConfig.class)
@SpringBootApplication
public class ItemApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

}
