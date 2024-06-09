package dev.jihogrammer.logtracer.adaptor.v5.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class V1ContextTest {

    @Test
    void v0() {
        logic1();
        logic2();
    }

    private void logic1() {
        var s = System.currentTimeMillis();

        businessLogic:
        {
            log.info("logic1() do something...");
        }

        var elapsed = System.currentTimeMillis() - s;
        log.info("logic1() elapsed=[{} ms]", elapsed);
    }

    private void logic2() {
        var s = System.currentTimeMillis();

        businessLogic:
        {
            log.info("logic2() do something...");
        }

        var elapsed = System.currentTimeMillis() - s;
        log.info("logic2() elapsed=[{} ms]", elapsed);
    }

    @Test
    void v1() {
        var strategy1 = new StrategyAlgorithm1();
        var strategy2 = new StrategyAlgorithm2();

        var context1 = new V1Context(strategy1);
        var context2 = new V1Context(strategy2);

        context1.execute();
        context2.execute();
    }

    @Test
    void v2() {
        var context1 = new V1Context(() -> log.info("anonymous1.call() do something..."));
        var context2 = new V1Context(() -> log.info("anonymous2.call() do something..."));

        context1.execute();
        context2.execute();
    }

}
