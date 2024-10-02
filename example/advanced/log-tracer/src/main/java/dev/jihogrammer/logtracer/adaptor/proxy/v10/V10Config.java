package dev.jihogrammer.logtracer.adaptor.proxy.v10;

import dev.jihogrammer.logtracer.adaptor.aop.LoggingTraceAspect;
import dev.jihogrammer.logtracer.adaptor.tracer.TracerConfig;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = TracerConfig.class)
@ComponentScan(basePackages = "dev.jihogrammer.logtracer.adaptor.app.v2_component_scan")
public class V10Config {

    @Bean
    LoggingTraceAspect loggingTraceAspect(final Tracer tracer) {
        return new LoggingTraceAspect(tracer);
    }

}
