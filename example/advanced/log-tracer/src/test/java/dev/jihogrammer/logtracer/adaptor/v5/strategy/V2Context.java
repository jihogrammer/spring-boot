package dev.jihogrammer.logtracer.adaptor.v5.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * Strategy Argument
 */
@Slf4j
class V2Context {

    void execute(final Strategy strategy) {
        var s = System.currentTimeMillis();

        strategy.call();

        var elapsed = System.currentTimeMillis() - s;
        log.info("{} elapsed=[{} ms]", strategy.getClass().getSimpleName(), elapsed);
    }

}
