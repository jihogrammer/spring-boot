package dev.jihogrammer.aop.warning;

import dev.jihogrammer.aop.member.MemberGreeting;
import dev.jihogrammer.aop.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThat;

class TypeCastingTest {

    @Test
    void jdkDynamicProxy() {
        // given
        var proxyFactory = new ProxyFactory(new MemberService());
        proxyFactory.setProxyTargetClass(false);

        // when
        var proxy = proxyFactory.getProxy();

        // then
        assertThat(MemberGreeting.class.isAssignableFrom(proxy.getClass())).isTrue();
        assertThat(MemberService.class.isAssignableFrom(proxy.getClass())).isFalse();
    }

    @Test
    void cglibProxy() {
        // given
        var proxyFactory = new ProxyFactory(new MemberService());
        proxyFactory.setProxyTargetClass(true);

        // when
        var proxy = proxyFactory.getProxy();

        // then
        assertThat(MemberGreeting.class.isAssignableFrom(proxy.getClass())).isTrue();
        assertThat(MemberService.class.isAssignableFrom(proxy.getClass())).isTrue();
    }

}
