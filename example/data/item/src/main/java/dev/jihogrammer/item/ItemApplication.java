package dev.jihogrammer.item;

import dev.jihogrammer.item.adaptor.web.HomeController;
import dev.jihogrammer.item.application.config.JdbcTemplateAppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(JdbcTemplateAppConfig.class)
@SpringBootApplication(scanBasePackageClasses = HomeController.class)
public class ItemApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

}
