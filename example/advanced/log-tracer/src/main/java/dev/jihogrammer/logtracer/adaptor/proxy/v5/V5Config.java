package dev.jihogrammer.logtracer.adaptor.proxy.v5;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V5Config {

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    V5OrderAdaptor orders(final Tracer tracer) {
        return new V5ProxyOrderAdaptor(tracer);
    }

    @Bean
    V5OrderService orderPort(final V5OrderAdaptor orders, final Tracer tracer) {
        return new V5ProxyOrderService(orders, tracer);
    }

    @Bean
    V5OrderController orderController(final V5OrderService orderPort, final Tracer tracer) {
        return new V5ProxyOrderController(orderPort, tracer);
    }

}
