package dev.jihogrammer.logtracer.adaptor.proxy.proxy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ProxyPatternClient {

    private final Subject subject;

    String execute() {
        return this.subject.operation();
    }

}
