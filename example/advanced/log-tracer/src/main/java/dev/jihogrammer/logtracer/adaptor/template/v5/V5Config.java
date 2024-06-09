package dev.jihogrammer.logtracer.adaptor.template.v5;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V5Config {

    @Bean
    Tracer threadLocalTracer() {
        return new TracerFactory().tracer();
    }

}
