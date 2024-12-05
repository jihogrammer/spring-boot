package dev.jihogrammer.spring.boot.autoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AutoConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AutoConfigApplication.class, args);
    }

    @RestController
    static class HealthController {

        @GetMapping("/health")
        String health() {
            return "OK";
        }

    }

}
