package dev.jihogrammer.logtracer.adaptor.proxy.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class DecoratorPatternClient {

    private final Component component;

    String execute() {
        var result = this.component.operate();

        log.info("result={}", result);

        return result;
    }

}
