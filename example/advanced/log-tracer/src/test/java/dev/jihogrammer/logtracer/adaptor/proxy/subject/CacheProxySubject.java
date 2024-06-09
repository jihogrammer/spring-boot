package dev.jihogrammer.logtracer.adaptor.proxy.subject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class CacheProxySubject implements Subject {

    private final Subject delegate;

    private String cache;

    @Override
    public String operation() {
        log.info("Operate CacheProxySubject");

        if (this.cache == null) {
            this.cache = this.delegate.operation();
        }

        return this.cache;
    }

}
