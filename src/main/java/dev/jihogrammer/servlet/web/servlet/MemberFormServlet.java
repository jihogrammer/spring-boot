package dev.jihogrammer.servlet.web.servlet;

import dev.jihogrammer.servlet.domain.member.MemberRepository;
import dev.jihogrammer.servlet.domain.member.SingletonMemoryMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/members/new-form")
public class MemberFormServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        ServletOutputStream out = response.getOutputStream();
        out.write("""
                <form action="/members/save" method="post">
                    username: <input type="text" name="username">
                         age: <input type="number" name="age">
                    <input type="submit" >
                </form>
                """.getBytes());
    }
}
