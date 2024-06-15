package dev.jihogrammer.logtracer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.jihogrammer.logtracer.adaptor.proxy.v4")
public class LogTracerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LogTracerApplication.class, args);
    }

}
