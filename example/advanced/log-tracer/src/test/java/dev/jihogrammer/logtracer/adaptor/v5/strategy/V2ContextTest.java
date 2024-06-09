package dev.jihogrammer.logtracer.adaptor.v5.strategy;

import org.junit.jupiter.api.Test;

class V2ContextTest {

    @Test
    void t1() {
        var context = new V2Context();

        context.execute(new StrategyAlgorithm1());
        context.execute(new StrategyAlgorithm2());
    }

    @Test
    void t2() {
        var context = new V2Context();

        context.execute(() -> System.out.println("Anonymous Strategy 1"));
        context.execute(() -> System.out.println("Anonymous Strategy 2"));
    }

}
