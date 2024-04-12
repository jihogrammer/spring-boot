package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.application.port.out.MemberPort;
import dev.jihogrammer.member.adaptor.in.web.entity.MemberViewModel;
import dev.jihogrammer.member.adaptor.in.web.viewresolver.ViewResolver;
import dev.jihogrammer.member.domain.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@WebServlet(urlPatterns = MembersServlet.URL)
public class MembersServlet extends HttpServlet {

    public static final String URL = "/members";

    private final ViewResolver membersViewResolver;

    private final MemberPort memberPort;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {} {}", request.getMethod(), URL);

        final var members = this.memberPort.findAll();
        final var memberViewModels = MemberViewModel.of(members);
        log.info("found members {}", memberViewModels);

        request.setAttribute("members", memberViewModels);
        request.getRequestDispatcher(this.membersViewResolver.resolveGetView()).forward(request, response);
    }

}
