package dev.jihogrammer.members.adaptor.in.web;

import dev.jihogrammer.members.adaptor.in.web.session.SignInArgumentResolver;
import dev.jihogrammer.members.adaptor.in.web.interceptor.SignInCheckInterceptor;
import dev.jihogrammer.members.adaptor.in.web.entity.SignedInMember;
import dev.jihogrammer.web.core.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Session<SignedInMember> memberSession() {
        return new Session<>("signed-in-member");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SignInArgumentResolver(this.memberSession()));
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new SignInCheckInterceptor(this.memberSession()))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/error", "/**.js", "/**.css", "/**.ico",
                        "/members/sign-in", "/members/sign-up", "/members/logout");
    }

}
