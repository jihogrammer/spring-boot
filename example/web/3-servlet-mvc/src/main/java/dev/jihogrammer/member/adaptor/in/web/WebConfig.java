package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.ServletMVCApplication;
import dev.jihogrammer.member.adaptor.in.web.viewresolver.ViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan
public class WebConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ServletMVCApplication.class);
    }

    @Bean
    public ViewResolver signUpViewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix,
        @Value("${service.sign-up.get-view}") final String getViewName,
        @Value("${service.sign-up.post-view}") final String postViewName
    ) {
        return new ViewResolver(prefix, suffix, getViewName, postViewName);
    }

    @Bean
    public ViewResolver membersViewResolver(
        @Value("${spring.mvc.view.prefix}") final String prefix,
        @Value("${spring.mvc.view.suffix}") final String suffix,
        @Value("${service.members.view}") final String getViewName
    ) {
        return new ViewResolver(prefix, suffix, getViewName, null);
    }

}
