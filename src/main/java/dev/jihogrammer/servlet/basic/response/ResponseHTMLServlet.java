package dev.jihogrammer.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/response-html")
public class ResponseHTMLServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String html = """
                <html>
                <body>
                  <h1>HTML</h1>
                </body>
                </html>
                """;
        response.getWriter().write(html);
    }
}
