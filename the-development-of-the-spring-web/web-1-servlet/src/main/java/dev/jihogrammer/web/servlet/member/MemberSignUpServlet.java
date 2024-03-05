package dev.jihogrammer.web.servlet.member;

import dev.jihogrammer.member.port.in.MemberService;
import dev.jihogrammer.member.port.in.MemberSignInUsage;
import dev.jihogrammer.member.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@WebServlet(urlPatterns = "/members/sign-up")
public class MemberSignUpServlet extends HttpServlet {

    private static final String NAME_PARAMETER_NAME = "name";

    private static final String AGE_PARAMETER_NAME = "age";

    private final MemberSignUpUsage memberService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getOutputStream().write("""
                <form method="post">
                    username: <input type="text" name="name">
                         age: <input type="number" name="age">
                    <input type="submit" >
                </form>
                <a href="/">home</a>
                """.getBytes());
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        var name = request.getParameter(NAME_PARAMETER_NAME);
        var age = Integer.parseInt(request.getParameter(AGE_PARAMETER_NAME));
        var newMember = this.memberService.signUp(MemberSignUpCommand.builder()
            .name(name)
            .age(age)
            .build());

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
                <a href="/members">members</a>
                <a href="/">home</a>
                """.formatted(newMember.name(), newMember.id().value(), newMember.name(), newMember.age()).getBytes());
    }
}
