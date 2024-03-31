package dev.jihogrammer.web.core;

import dev.jihogrammer.web.core.elapsed.ElapsedInterceptor;
import dev.jihogrammer.web.core.memory.MemoryAgent;
import dev.jihogrammer.web.core.memory.MemoryController;
import dev.jihogrammer.web.core.transaction.TransactionLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WebCoreApplication.class, args);
    }

    @Configuration
    static class WebConfig implements WebMvcConfigurer {

        @Value("${service.core.transaction.header-name:transaction-id}")
        String transactionHeaderName;

        @Value("${service.core.transaction.key-name:transaction.id}")
        String transactionKeyName;

        @Value("${service.core.elapsed.excludes:''}")
        String[] elapsedExcludes;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            var excludes = Arrays.asList("/**.ico", "/**.img", "/**.css", "/**.js");

            registry.addInterceptor(new TransactionLoggingInterceptor(this.transactionHeaderName, this.transactionKeyName))
                    .order(Integer.MIN_VALUE)
                    .addPathPatterns("/**")
                    .excludePathPatterns(excludes);

            registry.addInterceptor(new ElapsedInterceptor())
                    .order(Integer.MAX_VALUE)
                    .addPathPatterns("/**")
                    .excludePathPatterns(excludes)
                    .excludePathPatterns(this.elapsedExcludes);
        }

    }

    @Configuration
    @ConditionalOnProperty("service.core.memory.enabled")
    @ComponentScan(basePackageClasses = MemoryController.class)
    static class MemoryConfig {

        @Bean
        MemoryAgent memoryAgent() {
            return new MemoryAgent();
        }

    }

}
