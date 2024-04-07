package dev.jihogrammer.members.infrastructure.adaptor.in;

import dev.jihogrammer.member.adpator.in.MemberSignInInteractor;
import dev.jihogrammer.member.adpator.in.MemberSignUpInteractor;
import dev.jihogrammer.member.port.in.MemberSignInUsage;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import dev.jihogrammer.member.port.out.Members;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MembersAdaptorInConfig {

    @Bean
    public MemberSignUpUsage signUpUsage(final Members members) {
        return new MemberSignUpInteractor(members);
    }

    @Bean
    public MemberSignInUsage signInUsage(final Members members) {
        return new MemberSignInInteractor(members);
    }

}
