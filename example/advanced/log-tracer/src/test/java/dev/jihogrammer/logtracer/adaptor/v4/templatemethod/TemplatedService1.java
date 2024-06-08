package dev.jihogrammer.logtracer.adaptor.v4.templatemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TemplatedService1 extends AbstractTemplate {

    @Override
    protected void call() {
        log.info("logic1() do something...");
    }

}
