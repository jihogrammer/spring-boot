package dev.jihogrammer.logtracer.adaptor.template.v3.concurrency;

import lombok.extern.slf4j.Slf4j;

import static dev.jihogrammer.logtracer.application.utils.SleepUtils.sleep;

@Slf4j
class FieldService {

    private String store;

    String store(final String name) {
        log.info("name=[{}] --> store=[{}]", name, this.store);
        this.store = name;

        sleep();

        log.info("saved name. store=[{}]", this.store);
        return this.store;
    }

}
