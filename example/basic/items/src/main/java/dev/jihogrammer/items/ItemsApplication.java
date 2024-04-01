package dev.jihogrammer.items;

import dev.jihogrammer.web.core.WebCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemsApplication extends WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }

}
