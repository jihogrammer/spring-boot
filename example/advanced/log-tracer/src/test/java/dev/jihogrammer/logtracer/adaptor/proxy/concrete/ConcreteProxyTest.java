package dev.jihogrammer.logtracer.adaptor.proxy.concrete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ConcreteProxyTest {

    @Test
    void nonProxy() {
        var service = new ConcreteService();
        var client = new ConcreteClient(service);

        client.execute();
    }

    @Test
    void proxy() {
        var service = new ConcreteService();
        var proxy = new ConcreteTimeProxyService(service);
        var client = new ConcreteClient(proxy);

        client.execute();
    }

}
