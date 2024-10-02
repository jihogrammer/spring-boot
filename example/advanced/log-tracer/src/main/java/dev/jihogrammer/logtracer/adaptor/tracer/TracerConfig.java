package dev.jihogrammer.logtracer.adaptor.tracer;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfig {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

}
