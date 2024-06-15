package dev.jihogrammer.logtracer.adaptor.proxy.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

class JDKDynamicProxyTest {

    @Test
    void aInterface() {
        // given
        var handler = new ElapsedInvocationHandler(new AInterface() {
            @Override
            public String callA() {
                return AInterface.super.callA();
            }
        });

        // when
        var proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler);

        // then
        proxy.callA();
        assertThat(proxy.getClass().getName()).contains("jdk.proxy2.$Proxy");
    }

}
