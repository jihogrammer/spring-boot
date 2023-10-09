package dev.jihogrammer.servlet.web;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.MemberRepository;
import dev.jihogrammer.servlet.member.SingletonMemoryMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/members")
public class MemberListServlet extends HttpServlet {
    private static final String TABLE_FORMAT = "<table><thead><tr><th>ID</th><th>Username</th><th>Age</th></tr></thead><tbody>%s</tbody></table>";

    private final MemberRepository repository = SingletonMemoryMemberRepository.getInstance();

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        for (Member member : repository.findAll()) {
            sb.append("<tr>")
                    .append("<td>").append(member.getId()).append("</td>")
                    .append("<td>").append(member.getUsername()).append("</td>")
                    .append("<td>").append(member.getAge()).append("</td>")
                    .append("</tr>");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(TABLE_FORMAT.formatted(sb));
    }
}
