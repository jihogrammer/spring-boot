package dev.jihogrammer.item.login;

import dev.jihogrammer.member.port.in.MemberService;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoginApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LoginApplication.class, args);
    }

    @Bean
    @SuppressWarnings("unused")
    public MemberService memberService() {
        return new MemberService(SingletonInMemoryMemberRepository.getInstance());
    }

}
