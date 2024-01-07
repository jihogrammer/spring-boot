package dev.jihogrammer.item.login;

import dev.jihogrammer.item.login.adaptor.member.MemberInMemoryRepository;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import dev.jihogrammer.member.port.in.MemberRegisterUsage;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.service.MemberLoginService;
import dev.jihogrammer.member.service.MemberRegisterService;
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
        return new MemberInMemoryRepository();
    }

    @Bean
    public MemberRegisterUsage memberRegisterUsage(final Members members) {
        return new MemberRegisterService(members);
    }

    @Bean
    public MemberLoginUsage memberLoginUsage(final Members members) {
        return new MemberLoginService(members);
    }
}
