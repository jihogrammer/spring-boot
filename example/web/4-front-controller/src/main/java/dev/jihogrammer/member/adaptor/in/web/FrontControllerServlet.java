package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.adaptor.in.web.frontcontroller.AdapterMapper;
import dev.jihogrammer.member.adaptor.in.web.frontcontroller.ControllerResolver;
import dev.jihogrammer.member.adaptor.in.web.frontcontroller.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static dev.jihogrammer.member.adaptor.in.web.WebEnvironment.URI_PREFIX;

@WebServlet(urlPatterns = URI_PREFIX + "/*")
@Slf4j
@RequiredArgsConstructor
public class FrontControllerServlet extends HttpServlet {

    private final ControllerResolver controllerResolver;

    private final ViewResolver viewResolver;

    private final AdapterMapper adapterMapper;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException
    {
        var requestURI = request.getRequestURI();
        log.info("request URI: {}", requestURI);

        var controller = this.controllerResolver.resolve(requestURI, response);
        var adapter = this.adapterMapper.map(controller);
        var modelView = adapter.handle(request, response, controller);
        var view = this.viewResolver.resolve(modelView.viewName());
        log.info("modelView={}", modelView);

        view.render(modelView.model(), request, response);
    }

}
