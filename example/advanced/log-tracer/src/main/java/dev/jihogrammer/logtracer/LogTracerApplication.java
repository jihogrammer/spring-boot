package dev.jihogrammer.logtracer;

import dev.jihogrammer.logtracer.application.service.v0.V0Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = V0Config.class)
public class LogTracerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LogTracerApplication.class, args);
    }

}
