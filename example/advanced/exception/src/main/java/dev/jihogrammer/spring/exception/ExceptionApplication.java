package dev.jihogrammer.spring.exception;

import dev.jihogrammer.web.core.WebCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExceptionApplication extends WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ExceptionApplication.class, args);
    }

}
