package dev.jihogrammer.logtracer.adaptor.proxy.concrete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
class ConcreteTimeProxyService extends ConcreteService {

    private final ConcreteService delegate;

    @Override
    String operate() {
        log.info("ConcreteTimeProxyService operate");

        var startTime = System.currentTimeMillis();
        var result = delegate.operate();
        log.info("elapsed {} ms", System.currentTimeMillis() - startTime);

        return result;
    }

}
