package dev.jihogrammer.logtracer.adaptor.proxy.subject;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyPatternTest {

    @Test
    void beforeProxy() {
        var subject = new RealSubject();
        var client = new ProxyPatternClient(subject);

        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxy() {
        // given
        var delegate = new RealSubject();
        var subject = new CacheProxySubject(delegate);
        var client = new ProxyPatternClient(subject);

        // when
        var r1 = client.execute();
        var r2 = client.execute();
        var r3 = client.execute();

        // then
        assertThat(r1).isEqualTo(r2).isEqualTo(r3);
    }

}
