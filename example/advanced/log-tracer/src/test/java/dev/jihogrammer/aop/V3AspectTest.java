package dev.jihogrammer.aop;

import dev.jihogrammer.aop.aspect.V2Aspect;
import dev.jihogrammer.aop.aspect.V3Aspect;
import dev.jihogrammer.aop.order.OrderRepository;
import dev.jihogrammer.aop.order.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.springframework.aop.support.AopUtils.isAopProxy;

@Slf4j
@SpringBootTest(classes = {AopApplication.class})
@Import(V3Aspect.class)
class V3AspectTest {

    @Test
    void logInfo(
            @Autowired final OrderRepository repository,
            @Autowired final OrderService service
    ) {
        assertThat(isAopProxy(repository)).isTrue();
        assertThat(isAopProxy(service)).isTrue();
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
