package dev.jihogrammer.web.servletmvc;

import dev.jihogrammer.member.port.in.MemberService;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import dev.jihogrammer.web.servletmvc.view.ViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class ServletMVCApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ServletMVCApplication.class, args);
    }

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

    @Bean
    public Members members() {
        return SingletonInMemoryMemberRepository.getInstance();
    }

    @Bean
    public MemberService memberService(final Members members) {
        return new MemberService(members);
    }

}
