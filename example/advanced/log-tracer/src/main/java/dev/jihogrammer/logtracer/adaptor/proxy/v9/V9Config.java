package dev.jihogrammer.logtracer.adaptor.proxy.v9;

import dev.jihogrammer.logtracer.adaptor.aop.LoggingAdvice;
import dev.jihogrammer.logtracer.adaptor.tracer.TracerConfig;
import dev.jihogrammer.logtracer.application.port.in.Tracer;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TracerConfig.class)
class V9Config {

    private static final String[] PATTERNS = {"save*", "order*", "request*"};

    @Bean
    Advisor loggingAdvisor(final Tracer tracer) {
        final var pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("""
                execution(* dev.jihogrammer.logtracer.adaptor..*(..))
                && !execution(* dev.jihogrammer.logtracer.adaptor..noLog(..))""");

        final var advice = new LoggingAdvice(tracer);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

}
