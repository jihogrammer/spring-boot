package dev.jihogrammer.logtracer.adaptor.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ConcreteService {

    void call() {
        log.info("call ConcreteService");
    }

}
