package dev.jihogrammer.logtracer.adaptor.proxy.v2;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V2Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    V2OrderAdaptor orders(final Tracer tracer) {
        return new V2OrderAdaptor(tracer);
    }

    @Bean
    V2OrderService orderPort(final V2OrderAdaptor orders, final Tracer tracer) {
        return new V2OrderService(orders, tracer);
    }

    @Bean
    V2OrderController orderController(final V2OrderService orderPort, final Tracer tracer) {
        return new V2OrderController(orderPort, tracer);
    }

}
