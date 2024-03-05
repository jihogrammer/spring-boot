package dev.jihogrammer.web.servletmvc.controller;

import dev.jihogrammer.member.port.in.MemberService;
import dev.jihogrammer.member.model.MemberSignUpCommand;
import dev.jihogrammer.web.servletmvc.ServletMVCApplication;
import dev.jihogrammer.web.servletmvc.model.web.response.MemberView;
import dev.jihogrammer.web.servletmvc.view.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@SuppressWarnings("unused")
@Slf4j
@RequiredArgsConstructor
@WebServlet(urlPatterns = MemberSignUpServlet.URL)
public class MemberSignUpServlet extends HttpServlet {

    public static final String URL = "/members/sign-up";

    private static final String MEMBER_NAME_PARAMETER_NAME = "name";

    private static final String MEMBER_AGE_PARAMETER_NAME = "age";

    private static final String NEW_MEMBER_ATTRIBUTE_NAME = "newMember";

    /**
     * @see ServletMVCApplication#signUpViewResolver
     */
    private final ViewResolver signUpViewResolver;

    /**
     * @see ServletMVCApplication#memberService
     */
    private final MemberService memberService;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        log.info("REQUEST {} {}", request.getMethod(), URL);
        request.getRequestDispatcher(this.signUpViewResolver.resolveGetView()).forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        var name = request.getParameter(MEMBER_NAME_PARAMETER_NAME);
        var age = Integer.parseInt(request.getParameter(MEMBER_AGE_PARAMETER_NAME));
        log.info("REQUEST {} {}, name=[{}], age=[{}]", request.getMethod(), URL, name, age);

        var command = MemberSignUpCommand.builder().name(name).age(age).build();
        var signedUpMember = MemberView.of(this.memberService.signUp(command));
        log.info("singed up member [{}]", signedUpMember);

        request.setAttribute(NEW_MEMBER_ATTRIBUTE_NAME, signedUpMember);
        request.getRequestDispatcher(this.signUpViewResolver.resolvePostView()).forward(request, response);
    }

}
