package dev.jihogrammer.logtracer.adaptor.proxy.proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

@Slf4j
class RealSubject implements Subject {

    @Override
    public String operation() {
        log.info("Operate real subject");

        sleep();

        return UUID.randomUUID().toString().substring(0, 8);
    }

}
