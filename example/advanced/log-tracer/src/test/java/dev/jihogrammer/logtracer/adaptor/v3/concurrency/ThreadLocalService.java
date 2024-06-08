package dev.jihogrammer.logtracer.adaptor.v3.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ThreadLocalService {

    private final ThreadLocal<String> store = ThreadLocal.withInitial(() -> null);

    String store(final String name) {
        log.info("name=[{}] --> store=[{}]", name, this.store.get());
        this.store.set(name);

        sleep();

        log.info("saved name. store=[{}]", this.store.get());
        return this.store.get();
    }

    private void sleep() {
        try {
            Thread.sleep((long) (Math.random() * 800) + 200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
