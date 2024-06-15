package dev.jihogrammer.logtracer.adaptor.proxy.v6;

import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
class V6Config {

    private static final String[] PATTERNS = {
            "save*", "order*", "request*"
    };

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    Orders orders(final Tracer tracer) {
        return (Orders) Proxy.newProxyInstance(
                V6OrderAdaptor.class.getClassLoader(),
                new Class<?>[] {Orders.class},
                new LoggingProxyHandler(new V6OrderAdaptor(), tracer, PATTERNS));
    }

    @Bean
    OrderPort orderPort(final Orders orders, final Tracer tracer) {
        return (OrderPort) Proxy.newProxyInstance(
                V6OrderService.class.getClassLoader(),
                new Class<?>[] {OrderPort.class},
                new LoggingProxyHandler(new V6OrderService(orders), tracer, PATTERNS));
    }

    @Bean
    OrderController orderController(final OrderPort orderPort, final Tracer tracer) {
        return (OrderController) Proxy.newProxyInstance(
                V6OrderController.class.getClassLoader(),
                new Class<?>[] {OrderController.class},
                new LoggingProxyHandler(new V6OrderController(orderPort), tracer, PATTERNS));
    }

}
