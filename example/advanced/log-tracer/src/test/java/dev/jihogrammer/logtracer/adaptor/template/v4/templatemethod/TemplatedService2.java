package dev.jihogrammer.logtracer.adaptor.template.v4.templatemethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TemplatedService2 extends AbstractTemplate {

    @Override
    void call() {
        log.info("logic2() do something...");
    }

}
