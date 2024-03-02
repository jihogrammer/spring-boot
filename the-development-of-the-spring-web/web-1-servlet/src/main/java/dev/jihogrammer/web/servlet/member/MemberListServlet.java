package dev.jihogrammer.web.servlet.member;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/members")
public class MemberListServlet extends HttpServlet {
    private static final String TABLE_FORMAT = """
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Age</th>
            </tr>
          </thead>
          <tbody>%s</tbody>
        </table>
        <a href="/">home</a>
        """;

    private final Members repository = SingletonInMemoryMemberRepository.getInstance();

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        var sb = new StringBuilder();
        for (var member : repository.findAll()) {
            sb
                .append("<tr>")
                    .append("<td>").append(member.id()).append("</td>")
                    .append("<td>").append(member.name()).append("</td>")
                    .append("<td>").append(member.age()).append("</td>")
                .append("</tr>");
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(TABLE_FORMAT.formatted(sb));
    }
}
