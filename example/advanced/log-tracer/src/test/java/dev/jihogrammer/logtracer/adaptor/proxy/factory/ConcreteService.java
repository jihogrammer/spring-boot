package dev.jihogrammer.logtracer.adaptor.proxy.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ConcreteService {

    void doSomething() {
        log.info("Doing something...");
    }

}
