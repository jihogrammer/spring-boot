package dev.jihogrammer.aop.order;

import dev.jihogrammer.aop.AopApplication;
import dev.jihogrammer.aop.aspect.V1Aspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.aop.support.AopUtils.isAopProxy;

@Slf4j
@SpringBootTest(classes = {AopApplication.class, V1Aspect.class})
class AopTest {

    @Test
    void logInfo(
            @Autowired final OrderRepository repository,
            @Autowired final OrderService service
    ) {
        log.info("isAopProxy(repository) -> {}", isAopProxy(repository));
        log.info("isAopProxy(service) -> {}", isAopProxy(service));
    }

    @Test
    void execution(@Autowired final OrderService service) {
        assertThatCode(() -> service.orderItem("anItem")).doesNotThrowAnyException();
    }

    @Test
    void exception(@Autowired final OrderService service) {
        assertThatCode(() -> service.orderItem("ex")).isInstanceOf(IllegalStateException.class);
    }

}
