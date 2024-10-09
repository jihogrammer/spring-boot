package dev.jihogrammer.logtracer.adaptor.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
class ElapsedAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info(">>> ELAPSED PROXY;");
        var s = System.currentTimeMillis();

        var result = invocation.proceed();

        log.info("<<< ELAPSED PROXY; result={} ({} ms);", result, System.currentTimeMillis() - s);
        return result;
    }

}
