package dev.jihogrammer.exercise;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;

import static org.assertj.core.api.Assertions.*;

class BeanPostProcessorTest {

    @Test
    void common() {
        // given
        var context = new AnnotationConfigApplicationContext(CommonTestConfig.class);

        // when
        var beanA = context.getBean("beanA", A.class);

        // then
        assertThat(beanA).isNotNull();
        assertThatCode(beanA::hello).doesNotThrowAnyException();
        assertThatThrownBy(() -> context.getBean("beanB", B.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Test
    void convertingA2B() {
        // given
        var context = new AnnotationConfigApplicationContext(BeanPostProcessorTestConfig.class);

        // when
        var beanB = context.getBean("beanA", B.class);

        // then
        assertThat(beanB).isNotNull();
        assertThatCode(beanB::hello).doesNotThrowAnyException();
        assertThatThrownBy(() -> context.getBean("beanB", B.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @TestConfiguration
    static class CommonTestConfig {
        @Bean
        A beanA() {
            return new A();
        }
    }

    @TestConfiguration
    static class BeanPostProcessorTestConfig {
        @Bean
        A beanA() {
            return new A();
        }

        @Bean
        BeanPostProcessor beanPostProcessor() {
            return new ConvertingA2BBeanPostProcessor();
        }
    }

    @Slf4j
    static class ConvertingA2BBeanPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
            log.info("beanName={}; bean={};", beanName, bean);

            if (A.class.isAssignableFrom(bean.getClass())) {
                return new B();
            }

            return bean;
        }
    }

    @Slf4j
    static class A {
        void hello() {
            log.info("Hello, {}!", this.getClass().getSimpleName());
        }
    }

    @Slf4j
    static class B {
        void hello() {
            log.info("Hello, {}!", this.getClass().getSimpleName());
        }
    }

}
