package dev.jihogrammer.logtracer.adaptor.proxy.reflection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
class ElapsedInvocationHandler implements InvocationHandler {

    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info(">>> ELAPSED PROXY");
        var s = System.currentTimeMillis();

        var result = method.invoke(target, args);

        log.info("<<< ELAPSED PROXY - result={} ({} ms)", result, System.currentTimeMillis() - s);
        return result;
    }

}
