package dev.jihogrammer.logtracer.adaptor.proxy.cglib;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
class ElapsedMethodInterceptor implements MethodInterceptor {

    private final Object target;

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info(">>> ELAPSED PROXY");
        var s = System.currentTimeMillis();

        var result = proxy.invoke(target, args);

        log.info("<<< ELAPSED PROXY - result={} ({} ms)", result, System.currentTimeMillis() - s);
        return result;
    }

}
