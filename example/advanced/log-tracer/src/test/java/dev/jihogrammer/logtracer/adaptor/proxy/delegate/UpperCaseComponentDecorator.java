package dev.jihogrammer.logtracer.adaptor.proxy.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class UpperCaseComponentDecorator implements Component {

    private final Component delegate;

    @Override
    public String operate() {
        log.info("UpperCaseComponentDecorator operate");

        var result = delegate.operate();

        log.info("before result={}", result);

        return result.toUpperCase();
    }

}
