package dev.jihogrammer.item.login.web.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        registry.addInterceptor(new TransactionLoggingInterceptor())
            .order(1)
            .addPathPatterns("/**")
            .excludePathPatterns("/**.css", "/*.ico", "/error");

        registry.addInterceptor(new SignInCheckInterceptor())
            .order(2)
            .addPathPatterns("/**")
            .excludePathPatterns("/", "/sign-in", "/sign-up", "/logout", "/**.css", "/*.ico", "/error");
    }
}
