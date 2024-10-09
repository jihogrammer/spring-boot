package dev.jihogrammer.logtracer.adaptor.proxy.reflection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

@Slf4j
class ReflectionTest {

    @Test
    void problem() {
        var target = new Target();

        commonFeature(target::callA);
        commonFeature(target::callB);
    }

    @Test
    void reflectMethod() {
        var target = new Target();

        commonFeature(() -> {
            try {
                return Target.class.getMethod("callA").invoke(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        commonFeature(() -> {
            try {
                return Target.class.getMethod("callB").invoke(target);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    void commonFeature(Supplier<?> targetMethod) {
        log.info("call targetMethod='{}'", targetMethod.getClass().getName());
        var result = targetMethod.get();
        log.info("result={}", result);
    }

    static class Target {

        public String callA() {
            return "A";
        }

        public String callB() {
            return "B";
        }

    }

}
