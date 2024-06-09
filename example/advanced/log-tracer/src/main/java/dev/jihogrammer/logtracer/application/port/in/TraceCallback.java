package dev.jihogrammer.logtracer.application.port.in;

public interface TraceCallback<T> {

    T call();

}
