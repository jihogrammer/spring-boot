package dev.jihogrammer.logtracer.adaptor.v5.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Strategy Field
 */
@Slf4j
@RequiredArgsConstructor
class V1Context {

    private final Strategy strategy;

    void execute() {
        var s = System.currentTimeMillis();

        // delegation
        this.strategy.call();

        var elapsed = System.currentTimeMillis() - s;
        log.info("{} elapsed=[{} ms]", this.strategy.getClass().getSimpleName(), elapsed);
    }

}
