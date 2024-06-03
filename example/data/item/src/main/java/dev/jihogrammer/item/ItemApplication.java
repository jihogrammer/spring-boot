package dev.jihogrammer.item;

import dev.jihogrammer.item.adaptor.local.LocalDataInitializer;
import dev.jihogrammer.item.adaptor.web.HomeController;
import dev.jihogrammer.item.application.config.JpaAppConfig;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Import(JpaAppConfig.class)
@SpringBootApplication(scanBasePackageClasses = HomeController.class)
public class ItemApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }

    @Bean
    @Profile("local")
    public LocalDataInitializer localDataInitializer(final ItemUpdatePort itemUpdatePort) {
        return new LocalDataInitializer(itemUpdatePort);
    }

}
