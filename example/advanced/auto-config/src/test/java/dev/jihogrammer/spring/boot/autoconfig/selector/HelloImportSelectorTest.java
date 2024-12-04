package dev.jihogrammer.spring.boot.autoconfig.selector;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

class HelloImportSelectorTest {

    @Test
    void staticConfig() {
        // given
        var context = new AnnotationConfigApplicationContext(StaticConfig.class);

        // when
        var bean = context.getBean(HelloBean.class);

        // then
        assertThat(bean).isNotNull();
    }

    @Test
    void selectorConfig() {
        // given
        var context = new AnnotationConfigApplicationContext(SelectorConfig.class);

        // when
        var bean = context.getBean(HelloBean.class);

        // then
        assertThat(bean).isNotNull();
    }

    @Configuration
    @Import(HelloConfig.class)
    static class StaticConfig {
    }

    @Configuration
    @Import(HelloImportSelector.class)
    static class SelectorConfig {
    }

}
