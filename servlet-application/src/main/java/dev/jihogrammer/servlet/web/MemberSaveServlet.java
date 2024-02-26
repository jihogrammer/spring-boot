package dev.jihogrammer.servlet.web;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/members/save")
public class MemberSaveServlet extends HttpServlet {
    private static final String USERNAME_PARAMETER_NAME = "username";
    private static final String AGE_PARAMETER_NAME = "age";

    private final Members repository = SingletonInMemoryMemberRepository.getInstance();

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter(USERNAME_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(AGE_PARAMETER_NAME));

        Member newMember = repository.register(MemberRegisterCommand.builder()
            .name(username)
            .age(age)
            .build());

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        ServletOutputStream out = response.getOutputStream();
        out.write("""
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
                """
                .formatted(newMember.name(), newMember.id().value(), newMember.name(), newMember.age())
                .getBytes());
    }
}
