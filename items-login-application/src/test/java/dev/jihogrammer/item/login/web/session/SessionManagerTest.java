package dev.jihogrammer.item.login.web.session;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.model.vo.MemberId;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Optional;

import static dev.jihogrammer.member.Member.MemberFactory.makeMember;
import static org.assertj.core.api.Assertions.assertThat;

class SessionManagerTest {
    static SessionManager sessionManager = new SessionManager();

    @Test
    void testSessionManager() {
        Member member = makeMember(new MemberId(1), new MemberRegisterCommand("jihogrammer", "qwerty1234"));
        MockHttpServletResponse response = new MockHttpServletResponse();
        sessionManager.createSession(member, response);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        Optional<Member> optionalActualMember = sessionManager.findMemberByHttpServletRequest(request);
        assertThat(optionalActualMember.isPresent()).isTrue();
        assertThat(optionalActualMember.get()).isEqualTo(member);

        sessionManager.expire(request);
        Optional<Member> optionalExpiredMember = sessionManager.findMemberByHttpServletRequest(request);
        assertThat(optionalExpiredMember.isEmpty()).isTrue();
    }
}
