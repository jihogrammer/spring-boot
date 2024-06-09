package dev.jihogrammer.logtracer.adaptor.v8;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V8Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

}
