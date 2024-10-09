package dev.jihogrammer.logtracer.adaptor.proxy.delegate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class ElapsedComponentDecorator implements Component {

    private final Component delegate;

    @Override
    public String operate() {
        log.info("ElapsedComponentDecorator operate");

        var startTime = System.currentTimeMillis();
        var result = delegate.operate();
        log.info("elapsed {} ms", System.currentTimeMillis() - startTime);

        return result;
    }

}
