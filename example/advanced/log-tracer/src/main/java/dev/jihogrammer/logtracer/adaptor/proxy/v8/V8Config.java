package dev.jihogrammer.logtracer.adaptor.proxy.v8;

import dev.jihogrammer.logtracer.adaptor.aop.LoggingAdvice;
import dev.jihogrammer.logtracer.adaptor.beanpostprocessor.PackageTracerBeanPostProcessor;
import dev.jihogrammer.logtracer.adaptor.tracer.TracerConfig;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TracerConfig.class)
@ComponentScan(basePackages = "dev.jihogrammer.logtracer.adaptor.proxy.v8")
public class V8Config {

    private static final String[] PATTERNS = {"save*", "order*", "request*"};

    @Bean
    Advisor loggingAdvisor(final Tracer tracer) {
        final var pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames(PATTERNS);

        final var advice = new LoggingAdvice(tracer);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    BeanPostProcessor convertingToProxyBeanPostProcessor(final Advisor loggingTracerAdvisor) {
        return new PackageTracerBeanPostProcessor(this.getClass().getPackageName(), loggingTracerAdvisor);
    }

}
