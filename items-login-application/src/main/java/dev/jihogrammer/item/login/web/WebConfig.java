package dev.jihogrammer.item.login.web;

import dev.jihogrammer.item.login.web.interceptor.SignInCheckInterceptor;
import dev.jihogrammer.item.login.web.interceptor.TransactionLoggingInterceptor;
import dev.jihogrammer.item.login.web.session.SignInArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SignInArgumentResolver());
    }

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
