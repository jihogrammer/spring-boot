package dev.jihogrammer.members.application;

import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.members.application.signin.SignInArgumentResolver;
import dev.jihogrammer.members.application.signin.SignInCheckInterceptor;
import dev.jihogrammer.web.session.port.in.Session;
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
    public Session<Member> memberSession() {
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
