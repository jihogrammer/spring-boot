package dev.jihogrammer.logtracer.adaptor.v6;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V6Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    Orders orders(final Tracer tracer) {
        return new V6OrderAdaptor(tracer);
    }

    @Bean
    OrderPort orderPort(final Orders orders, final Tracer tracer) {
        return new V6OrderService(orders, tracer);
    }

    @Bean
    OrderController orderController(final OrderPort orderPort, final Tracer tracer) {
        return new V6OrderController(orderPort, tracer);
    }

}
