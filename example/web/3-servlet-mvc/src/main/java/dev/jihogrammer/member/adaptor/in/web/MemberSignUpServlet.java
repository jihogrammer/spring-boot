package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.adaptor.in.web.entity.MemberViewModel;
import dev.jihogrammer.member.adaptor.in.web.viewresolver.ViewResolver;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import dev.jihogrammer.member.domain.exception.MemberException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@WebServlet(urlPatterns = MemberSignUpServlet.URL)
public class MemberSignUpServlet extends HttpServlet {

    public static final String URL = "/members/sign-up";

    private final ViewResolver signUpViewResolver;

    private final MemberSignUpUseCase signUpUsage;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {} {}", request.getMethod(), URL);

        request.getRequestDispatcher(this.signUpViewResolver.resolveGetView()).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final var name = request.getParameter("name");
        final var age = Integer.parseInt(request.getParameter("age"));
        log.info("REQUEST {} {}; name=[{}]; age=[{}];", request.getMethod(), URL, name, age);

        try {
            final var command = MemberSignUpCommand.builder()
                .name(name)
                .age(age)
                .email(EmailGenerator.next())
                .build();
            final var member = this.signUpUsage.signUp(command);

            final var memberViewModel = MemberViewModel.of(member);
            log.info("singed up member [{}]", memberViewModel);

            request.setAttribute("member", memberViewModel);
            request.getRequestDispatcher(this.signUpViewResolver.resolvePostView()).forward(request, response);
        } catch (final MemberException e) {
            log.error("Failed to sign up.", e);
        }
    }

    private static class EmailGenerator {

        public static String next() {
            return UUID.randomUUID() + "@jihogrammer.dev";
        }

    }

}
