package dev.jihogrammer.member.adaptor.in.web.member;

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
@WebServlet(urlPatterns = "/members/sign-up")
public class MemberSignUpServlet extends HttpServlet {

    private final MemberSignUpUseCase memberSignUpUseCase;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getOutputStream().write("""
            <form method="post">
                name: <input type="text" name="name">
                age: <input type="number" name="age">
                <input type="submit" >
            </form>
            <a href="/">home</a>
            """.getBytes());
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final var name = request.getParameter("name");
        final var age = Integer.parseInt(request.getParameter("age"));
        final var command = MemberSignUpCommand.builder()
            .name(name)
            .email(name + "@jihogrammer.dev")
            .age(age)
            .build();
        final var newMember = this.memberSignUpUseCase.signUp(command);

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
                    <th>Name</th>
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
