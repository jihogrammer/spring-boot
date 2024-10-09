package dev.jihogrammer.logtracer.adaptor.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

class AdvisorTest {

    @Test
    void advisorTest() {
        // given
        var advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new ElapsedAdvice());
        var proxyFactory = new ProxyFactory(new ImplementedService());

        // when
        proxyFactory.addAdvisor(advisor);

        var service = (Service) proxyFactory.getProxy();
        service.doSomething();
    }

    @Test
    void pointcutTest() {
        // given
        var advisor = new DefaultPointcutAdvisor(new CustomPointcut(), new ElapsedAdvice());
        var proxyFactory = new ProxyFactory(new ImplementedService());

        // when
        proxyFactory.addAdvisor(advisor);

        var service = (Service) proxyFactory.getProxy();
        service.doSomething();
        service.doSomethingWithoutProxy();
    }

    @Test
    void springPointcutTest() {
        // given
        var pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("doSomething");

        var advisor = new DefaultPointcutAdvisor(pointcut, new ElapsedAdvice());
        var proxyFactory = new ProxyFactory(new ImplementedService());

        // when
        proxyFactory.addAdvisor(advisor);

        var service = (Service) proxyFactory.getProxy();
        service.doSomething();
        service.doSomethingWithoutProxy();
    }

    interface Service {

        void doSomething();

        void doSomethingWithoutProxy();

    }

    @Slf4j
    static class ImplementedService implements Service {

        @Override
        public void doSomething() {
            log.info("Doing something...");
        }

        @Override
        public void doSomethingWithoutProxy() {
            log.info("Doing something without proxy...");
        }

    }

    @Slf4j
    static class CustomPointcut implements Pointcut {

        private static final String EXPECTED_METHOD_NAME = "doSomething";

        @Override
        @NonNull
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        @NonNull
        public MethodMatcher getMethodMatcher() {
            return new MethodMatcher() {
                @Override
                public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass) {
                    boolean result = EXPECTED_METHOD_NAME.equals(method.getName());

                    log.info("matches result={}; methodName={}; targetClass={}", result, method.getName(), targetClass);

                    return result;
                }

                @Override
                public boolean isRuntime() {
                    return false;
                }

                @Override
                public boolean matches(@NonNull Method method, @NonNull Class<?> targetClass, @NonNull Object... args) {
                    throw new UnsupportedOperationException();
                }
            };
        }

    }

}
