package dev.jihogrammer.spring.boot.memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class MemoryConfigurationCondition implements Condition {

    @Override
    public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
        final var memoryCondition = context.getEnvironment().getProperty("memory");

        log.info("memoryCondition={}", memoryCondition);

        return "on".equalsIgnoreCase(memoryCondition);
    }

}
