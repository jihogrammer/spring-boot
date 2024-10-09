package dev.jihogrammer.logtracer.adaptor.proxy.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ServiceImpl implements Service {

    @Override
    public void save() {
        log.info("call save()");
    }

    @Override
    public void find() {
        log.info("call find()");
    }

}
