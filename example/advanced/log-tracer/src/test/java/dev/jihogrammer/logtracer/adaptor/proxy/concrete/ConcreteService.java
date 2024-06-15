package dev.jihogrammer.logtracer.adaptor.proxy.concrete;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
class ConcreteService {

    String operate() {
        log.info("do something...");
        return UUID.randomUUID().toString();
    }

}
