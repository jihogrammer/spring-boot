package dev.jihogrammer.web.core;

import dev.jihogrammer.web.core.elapsed.ElapsedInterceptor;
import dev.jihogrammer.web.core.transaction.TransactionLoggingFilter;
import dev.jihogrammer.web.core.transaction.TransactionLoggingInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class WebCoreConfig implements WebMvcConfigurer {

    @Value("${service.core.elapsed.excludes:/}")
    String[] elapsedExcludes;

    @Bean
    public FilterRegistrationBean<Filter> transactionFilter(
        @Value("${service.core.transaction.header-name:transaction-id}") final String txHeaderName,
        @Value("${service.core.transaction.key-name:transaction.id}") final String txMDCKey
    ) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new TransactionLoggingFilter(txHeaderName, txMDCKey));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns("/*");

        // TODO consider about dispatcher type - REQUEST(default), ERROR, and etc.
        // filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        var excludes = Arrays.asList("/**.ico", "/**.img", "/**.css", "/**.js");

        registry.addInterceptor(new ElapsedInterceptor())
                .order(Integer.MAX_VALUE)
                .addPathPatterns("/**")
                .excludePathPatterns(excludes)
                .excludePathPatterns(this.elapsedExcludes);
    }

}
