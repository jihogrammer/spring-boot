package dev.jihogrammer.web.servlet;

import dev.jihogrammer.member.adpator.in.MemberSignInInteractor;
import dev.jihogrammer.member.adpator.in.MemberSignUpInteractor;
import dev.jihogrammer.member.adpator.out.InMemoryMemberRepository;
import dev.jihogrammer.member.port.in.MemberSignInUsage;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import dev.jihogrammer.member.port.out.Members;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class ServletApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ServletApplication.class);
    }

    @Bean
    public Members members() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public MemberSignInUsage signInUsage(final Members members) {
        return new MemberSignInInteractor(members);
    }

    @Bean
    public MemberSignUpUsage signUpUsage(final Members members) {
        return new MemberSignUpInteractor(members);
    }

}
