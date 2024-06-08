package dev.jihogrammer.logtracer.adaptor.v4;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V4Config {

    @Bean
    Tracer threadLocalTracer() {
        return new TracerFactory().tracer();
    }

}
