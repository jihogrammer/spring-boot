package dev.jihogrammer.logtracer.adaptor.proxy.v1;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V1Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    Orders orders(final Tracer tracer) {
        return new V1OrderAdaptor(tracer);
    }

    @Bean
    OrderPort orderPort(final Orders orders, final Tracer tracer) {
        return new V1OrderService(orders, tracer);
    }

    @Bean
    OrderController orderController(final OrderPort orderPort, final Tracer tracer) {
        return new V1OrderController(orderPort, tracer);
    }

}
