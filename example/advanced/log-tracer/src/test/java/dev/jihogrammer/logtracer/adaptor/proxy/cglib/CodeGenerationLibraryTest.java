package dev.jihogrammer.logtracer.adaptor.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class CodeGenerationLibraryTest {

    @Test
    void concrete() {
        // given
        var service = new ConcreteService();

        // when
        var proxy = (ConcreteService) Enhancer.create(ConcreteService.class, new ElapsedMethodInterceptor(service));

        // then
        proxy.call();
        log.info("service class = {}", service.getClass());
        log.info("proxy class = {}", proxy.getClass());
        assertThat(proxy.getClass().getName()).contains("$$EnhancerByCGLIB$$");
    }

}
