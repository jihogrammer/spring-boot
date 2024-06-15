package dev.jihogrammer.logtracer.adaptor.proxy.concrete;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class ConcreteClient {

    private final ConcreteService service;

    void execute() {
        log.info("execute: {}", this.service.operate());
    }

}
