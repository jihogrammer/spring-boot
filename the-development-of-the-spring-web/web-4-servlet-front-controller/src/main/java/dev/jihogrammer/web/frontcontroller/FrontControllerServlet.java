package dev.jihogrammer.web.frontcontroller;

import dev.jihogrammer.web.frontcontroller.utils.AdapterMapper;
import dev.jihogrammer.web.frontcontroller.utils.ControllerResolver;
import dev.jihogrammer.web.frontcontroller.utils.ViewResolver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static dev.jihogrammer.web.frontcontroller.FrontControllerServlet.URI_PREFIX;

@WebServlet(urlPatterns = URI_PREFIX + "/*")
@Slf4j
@RequiredArgsConstructor
public class FrontControllerServlet extends HttpServlet {

    public static final String URI_PREFIX = "/front-controller";

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
        log.info("modelView={}", modelView);

        this.viewResolver.resolve(modelView.viewName()).render(modelView.model(), request, response);
    }

}
