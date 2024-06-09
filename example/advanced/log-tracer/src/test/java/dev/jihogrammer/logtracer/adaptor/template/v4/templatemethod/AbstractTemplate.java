package dev.jihogrammer.logtracer.adaptor.template.v4.templatemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class AbstractTemplate {

    abstract void call();

    void execute() {
        var s = System.currentTimeMillis();

        businessLogic:
        {
            this.call();
        }

        log.info("call() elapsed=[{} ms]", System.currentTimeMillis() - s);
    }

}
