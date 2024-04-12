package dev.jihogrammer.web.servlet.member;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.exception.MemberException;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@WebServlet(urlPatterns = "/memberPort/sign-up")
public class MemberSignUpServlet extends HttpServlet {

    private static final String NAME_PARAMETER_NAME = "username";

    private static final String AGE_PARAMETER_NAME = "age";

    private final MemberSignUpUseCase memberService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getOutputStream().write("""
                <form method="post">
                    username: <input type="text" username="username">
                         age: <input type="number" username="age">
                    <input type="submit" >
                </form>
                <a href="/">home</a>
                """.getBytes());
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        var name = request.getParameter(NAME_PARAMETER_NAME);
        var age = Integer.parseInt(request.getParameter(AGE_PARAMETER_NAME));
        Member newMember = null;
        try {
            newMember = this.memberService.signUp(MemberSignUpCommand.builder()
                .name(name)
                .age(age)
                .build());
        } catch (MemberException e) {
            throw new RuntimeException(e);
        }

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getOutputStream().write("""
                <h1>Hello, %s!</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <td>%d</td>
                    </tr>
                    <tr>
                        <th>Username</th>
                        <td>%s</td>
                    </tr>
                    <tr>
                        <th>Age</th>
                        <td>%d</td>
                    </tr>
                </table>
                <a href="/memberPort">memberPort</a>
                <a href="/">home</a>
                """.formatted(newMember.name(), newMember.id().value(), newMember.name(), newMember.age()).getBytes());
    }
}
