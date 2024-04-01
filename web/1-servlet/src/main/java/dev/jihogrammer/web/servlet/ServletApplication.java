package dev.jihogrammer.web.servlet;

import dev.jihogrammer.domain.members.port.in.SignInInteractor;
import dev.jihogrammer.domain.members.port.in.SignInUsage;
import dev.jihogrammer.domain.members.port.in.SignUpInteractor;
import dev.jihogrammer.domain.members.port.in.SignUpUsage;
import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import dev.jihogrammer.domain.members.port.out.Members;
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
    public SignInUsage signInUsage(final Members members) {
        return new SignInInteractor(members);
    }

    @Bean
    public SignUpUsage signUpUsage(final Members members) {
        return new SignUpInteractor(members);
    }

}
