package dev.jihogrammer.logtracer.adaptor.template.v5.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class StrategyAlgorithm2 implements Strategy {

    @Override
    public void call() {
        log.info("StrategyAlgorithm2.call() do something...");
    }

}
