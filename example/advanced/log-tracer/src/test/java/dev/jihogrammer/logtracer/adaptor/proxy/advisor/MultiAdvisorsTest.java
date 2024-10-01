package dev.jihogrammer.logtracer.adaptor.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

@Slf4j
class MultiAdvisorsTest {

    @Test
    void multiProxies() {
        // given
        Runnable target = () -> log.info("Doing something...");

        var primaryProxyFactory = new ProxyFactory(target);
        primaryProxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new PrimaryAdvice()));
        var secondaryProxyFactory = new ProxyFactory(primaryProxyFactory.getProxy());
        secondaryProxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new SecondaryAdvice()));

        // when
        ((Runnable) secondaryProxyFactory.getProxy()).run();
    }

    @Test
    void aProxy() {
        // given
        Runnable target = () -> log.info("Doing something...");

        var proxyFactory = new ProxyFactory(target);

        // 넣은 순서대로 동작하므로 순서에 유의할
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new SecondaryAdvice()));
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, new PrimaryAdvice()));

        // when
        ((Runnable) proxyFactory.getProxy()).run();
    }

    @Slf4j
    static class PrimaryAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                log.info(">>> {}", this.getClass().getSimpleName());
                return invocation.proceed();
            } finally {
                log.info("<<< {}", this.getClass().getSimpleName());
            }
        }
    }

    @Slf4j
    static class SecondaryAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            try {
                log.info(">>> {}", this.getClass().getSimpleName());
                return invocation.proceed();
            } finally {
                log.info("<<< {}", this.getClass().getSimpleName());
            }
        }
    }

}
