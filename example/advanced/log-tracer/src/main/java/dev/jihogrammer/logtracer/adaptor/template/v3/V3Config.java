package dev.jihogrammer.logtracer.adaptor.template.v3;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
class V3Config {

    @Bean
    Tracer fieldTracer() {
        return new FieldTracer();
    }

    @Bean
    @Primary
    Tracer threadLocalTracer() {
        return new TracerFactory().tracer();
    }

}
