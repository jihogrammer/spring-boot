package dev.jihogrammer.logtracer.adaptor.v4.templatemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class AbstractTemplate {

    protected abstract void call();

    public void execute() {
        var s = System.currentTimeMillis();

        businessLogic:
        {
            this.call();
        }

        log.info("call() elapsed=[{} ms]", System.currentTimeMillis() - s);
    }

}
