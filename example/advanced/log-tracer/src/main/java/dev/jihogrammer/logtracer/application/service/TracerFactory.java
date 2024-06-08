package dev.jihogrammer.logtracer.application.service;

import dev.jihogrammer.logtracer.application.port.in.Tracer;

public class TracerFactory {

    public Tracer tracer() {
        return new ThreadLocalTracer();
    }

}
