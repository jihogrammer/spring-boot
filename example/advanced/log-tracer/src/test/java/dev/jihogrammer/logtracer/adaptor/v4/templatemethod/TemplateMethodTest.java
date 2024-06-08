package dev.jihogrammer.logtracer.adaptor.v4.templatemethod;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class TemplateMethodTest {

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
        var template1 = new TemplatedService1();
        var template2 = new TemplatedService2();

        template1.execute();
        template2.execute();
    }

}
