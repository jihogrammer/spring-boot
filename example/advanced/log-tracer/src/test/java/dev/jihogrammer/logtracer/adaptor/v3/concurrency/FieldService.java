package dev.jihogrammer.logtracer.adaptor.v3.concurrency;

import lombok.extern.slf4j.Slf4j;

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

    private void sleep() {
        try {
            Thread.sleep((long) (Math.random() * 800) + 200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
