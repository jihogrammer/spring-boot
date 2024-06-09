package dev.jihogrammer.logtracer.adaptor.proxy.v3;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V3Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

}
