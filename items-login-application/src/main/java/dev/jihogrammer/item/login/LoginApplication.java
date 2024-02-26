package dev.jihogrammer.item.login;

import dev.jihogrammer.member.port.in.MemberLoginService;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import dev.jihogrammer.member.port.out.Members;
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
    public Members members() {
        return SingletonInMemoryMemberRepository.getInstance();
    }

    @Bean
    public MemberLoginUsage memberLoginUsage(final Members members) {
        return new MemberLoginService(members);
    }
}
