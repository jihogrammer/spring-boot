package dev.jihogrammer.web.core;

import dev.jihogrammer.web.core.elapsed.ElapsedInterceptor;
import dev.jihogrammer.web.core.transaction.TransactionLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebCoreConfig implements WebMvcConfigurer {

    @Value("${service.core.transaction.header-name:transaction-id}")
    String transactionHeaderName;

    @Value("${service.core.transaction.key-name:transaction.id}")
    String transactionKeyName;

    @Value("${service.core.elapsed.excludes:/}")
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
