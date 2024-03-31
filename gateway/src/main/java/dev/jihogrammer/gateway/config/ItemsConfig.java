package dev.jihogrammer.gateway.config;

//import dev.jihogrammer.item.login.LoginApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackageClasses = {
//                LoginApplication.class
        }
)
public class ItemsConfig {
}
