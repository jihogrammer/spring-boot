package dev.jihogrammer.logtracer;

import dev.jihogrammer.logtracer.adaptor.v1.V1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = V1Config.class)
public class LogTracerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LogTracerApplication.class, args);
    }

}
