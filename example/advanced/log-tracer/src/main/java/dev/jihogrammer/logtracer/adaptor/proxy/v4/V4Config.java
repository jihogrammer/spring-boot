package dev.jihogrammer.logtracer.adaptor.proxy.v4;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V4Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    Orders orders(final Tracer tracer) {
        return new V4LoggingProxyOrderAdaptor(new V4OrderAdaptor(), tracer);
    }

    @Bean
    OrderPort orderPort(final Orders orders, final Tracer tracer) {
        return new V4LoggingProxyOrderService(new V4OrderService(orders), tracer);
    }

    @Bean
    OrderController orderController(final OrderPort orderPort, final Tracer tracer) {
        return new V4LoggingProxyOrderController(new V4OrderController(orderPort), tracer);
    }

}
