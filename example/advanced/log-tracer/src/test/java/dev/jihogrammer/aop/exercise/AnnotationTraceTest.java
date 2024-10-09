package dev.jihogrammer.aop.exercise;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class AnnotationTraceTest {

    @Test
    void save(@Autowired final StrongService service) {
        service.trim(" jihogrammer ");
        service.trim(" jihogrammer ");
        service.trim(" jihogrammer ");
        service.trim(" jihogrammer ");
        service.trim(" jihogrammer ");
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        WeakRepository repository() {
            return new WeakRepository();
        }

        @Bean
        StrongService service(final WeakRepository repository) {
            return new StrongService(repository);
        }

        @Bean
        AnnotationAspect aspect() {
            return new AnnotationAspect();
        }
    }

}
