package dev.jihogrammer.logtracer.adaptor.v5.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class StrategyAlgorithm1 implements Strategy {

    @Override
    public void call() {
        log.info("StrategyAlgorithm1.call() do something...");
    }

}
