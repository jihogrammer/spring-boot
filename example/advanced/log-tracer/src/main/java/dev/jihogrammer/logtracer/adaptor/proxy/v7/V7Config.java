package dev.jihogrammer.logtracer.adaptor.proxy.v7;

import dev.jihogrammer.logtracer.adaptor.aop.LoggingAdvice;
import dev.jihogrammer.logtracer.application.port.in.OrderPort;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.port.out.Orders;
import dev.jihogrammer.logtracer.application.service.TracerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class V7Config {

    private static final String[] PATTERNS = {
            "save*", "order*", "request*"
    };

    @Bean
    Tracer tracer() {
        return new TracerFactory().tracer();
    }

    @Bean
    Advisor loggingAdvisor(final Tracer tracer) {
        final var pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERNS);

        final var advice = new LoggingAdvice(tracer);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    Orders orders(final Advisor loggingAdvisor) {
        final var proxyFactory = new ProxyFactory(new OrderAdaptor());

        proxyFactory.addAdvisor(loggingAdvisor);

        return (Orders) proxyFactory.getProxy();
    }

    @Bean
    OrderPort orderPort(final Orders orders, final Advisor loggingAdvisor) {
        final var proxyFactory = new ProxyFactory(new OrderService(orders));

        proxyFactory.addAdvisor(loggingAdvisor);

        return (OrderPort) proxyFactory.getProxy();
    }

    @Bean
    OrderController orderController(final OrderPort orderPort, final Advisor loggingAdvisor) {
        final var proxyFactory = new ProxyFactory(new V7OrderController(orderPort));

        proxyFactory.addAdvisor(loggingAdvisor);

        return (OrderController) proxyFactory.getProxy();
    }

}
