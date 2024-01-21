package dev.jihogrammer.exception.config;

import dev.jihogrammer.exception.filter.LoggingFilter;
import dev.jihogrammer.exception.interceptor.ElapsedLoggingInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<Filter> loggingFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new LoggingFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        // DispatcherType 값에 따라 동작 여부를 선택할 수 있다.
        // 만약 Filter 중복 처리를 막아야 한다면, DispatchType.ERROR 값을 제거하자 (기본값: REQUEST)
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);

        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(@NonNull final InterceptorRegistry registry) {
        registry.addInterceptor(new ElapsedLoggingInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/**.css", "/*.ico", "/error", "/error-page/**");
    }
}
