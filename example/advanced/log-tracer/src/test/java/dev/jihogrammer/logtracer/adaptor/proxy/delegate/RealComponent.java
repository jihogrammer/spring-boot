package dev.jihogrammer.logtracer.adaptor.proxy.delegate;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
class RealComponent implements Component {

    @Override
    public String operate() {
        log.info("Operate real component");

        return UUID.randomUUID().toString().substring(0, 8);
    }

}
