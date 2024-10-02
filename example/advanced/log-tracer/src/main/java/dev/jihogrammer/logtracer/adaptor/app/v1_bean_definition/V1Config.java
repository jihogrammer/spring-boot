package dev.jihogrammer.logtracer.adaptor.app.v1_bean_definition;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V1Config {

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
