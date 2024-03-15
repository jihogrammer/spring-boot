package dev.jihogrammer.spring.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication {

    public static final String APP_NAME = "Basic Application";

    public static void main(final String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

}
