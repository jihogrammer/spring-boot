package dev.jihogrammer.spring.basic.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ThymeleafApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ThymeleafApplication.class, args);
    }

    @Configuration
    @ComponentScan(basePackages = "dev.jihogrammer.spring.basic.http")
    public static class HttpConfig {}

}
