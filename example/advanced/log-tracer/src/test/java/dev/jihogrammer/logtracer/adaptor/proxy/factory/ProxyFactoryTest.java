package dev.jihogrammer.logtracer.adaptor.proxy.factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
    void interfaceProxy() {
        // given
        var proxyFactory = new ProxyFactory(new ServiceImpl());
        proxyFactory.addAdvice(new TimeAdvice());

        // when
        var proxy = (Service) proxyFactory.getProxy();
        proxy.save();

        // then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
    }

    @Test
    @DisplayName("구체클래스만 있으면 CGLIB 프록시 사용")
    void concreteCGLIBProxy() {
        // given
        var proxyFactory = new ProxyFactory(new ConcreteService());
        proxyFactory.addAdvice(new TimeAdvice());

        // when
        var proxy = (ConcreteService) proxyFactory.getProxy();
        proxy.doSomething();

        // then
        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue();
    }

    @Test
    @DisplayName("인터페이스가 있더라도 ProxyTargetClass 옵션을 사용하면 CGLIB 클래스 기반 프록시를 사용")
    void proxyTargetClass() {
        // given
        var proxyFactory = new ProxyFactory(new ServiceImpl());
        proxyFactory.addAdvice(new TimeAdvice());

        // when
        proxyFactory.setProxyTargetClass(true);

        // then
        assertThat(AopUtils.isCglibProxy(proxyFactory.getProxy())).isTrue();
    }

}
