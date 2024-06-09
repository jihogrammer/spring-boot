package dev.jihogrammer.logtracer.adaptor.v7;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V7Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    V7OrderAdaptor orders(final Tracer tracer) {
        return new V7OrderAdaptor(tracer);
    }

    @Bean
    V7OrderService orderPort(final V7OrderAdaptor orders, final Tracer tracer) {
        return new V7OrderService(orders, tracer);
    }

    @Bean
    V7OrderController orderController(final V7OrderService orderPort, final Tracer tracer) {
        return new V7OrderController(orderPort, tracer);
    }

}
