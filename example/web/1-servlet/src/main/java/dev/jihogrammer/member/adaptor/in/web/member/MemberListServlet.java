package dev.jihogrammer.member.adaptor.in.web.member;

import dev.jihogrammer.member.application.port.out.MemberPort;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/members")
@RequiredArgsConstructor
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

    private final MemberPort memberPort;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final var sb = new StringBuilder();

        for (var member : this.memberPort.findAll()) {
            sb
                .append("<tr>")
                    .append("<td>").append(member.id().value()).append("</td>")
                    .append("<td>").append(member.name()).append("</td>")
                    .append("<td>").append(member.age()).append("</td>")
                .append("</tr>");
        }

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.getWriter().write(TABLE_FORMAT.formatted(sb));
    }

}
