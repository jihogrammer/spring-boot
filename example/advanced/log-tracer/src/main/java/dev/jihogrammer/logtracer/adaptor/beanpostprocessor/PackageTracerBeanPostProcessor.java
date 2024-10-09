package dev.jihogrammer.logtracer.adaptor.beanpostprocessor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

@Slf4j
@RequiredArgsConstructor
public class PackageTracerBeanPostProcessor implements BeanPostProcessor {

    private final String basePackageName;

    private final Advisor advisor;

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        log.debug("beanName={}; beanClass={};", beanName, bean.getClass());

        if (isIgnored(bean)) {
            return bean;
        }

        return this.convertBeanToProxy(bean);
    }

    private boolean isIgnored(final Object bean) {
        return !bean.getClass().getPackageName().startsWith(this.basePackageName);
    }

    private Object convertBeanToProxy(final Object bean) {
        var proxyFactory = new ProxyFactory(bean);
        proxyFactory.addAdvisor(this.advisor);

        return proxyFactory.getProxy();
    }

}
