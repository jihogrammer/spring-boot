package dev.jihogrammer.front_controller;

import dev.jihogrammer.front_controller.controller.MemberFormController;
import dev.jihogrammer.front_controller.controller.MemberListController;
import dev.jihogrammer.front_controller.controller.MemberSaveController;
import dev.jihogrammer.front_controller.repository.SingletonInMemoryMembers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(urlPatterns = FrontControllerServlet.URI_PREFIX + "/*")
@Slf4j
public class FrontControllerServlet extends HttpServlet {
    public static final String URI_PREFIX = "/front-controller";

    private final Map<String, Controller> controllerMap;

    public FrontControllerServlet() {
        controllerMap = new HashMap<>();

        controllerMap.put(URI_PREFIX + "/members/new-form", new MemberFormController());
        controllerMap.put(URI_PREFIX + "/members/save", new MemberSaveController(SingletonInMemoryMembers.getInstance()));
        controllerMap.put(URI_PREFIX + "/members", new MemberListController(SingletonInMemoryMembers.getInstance()));
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException
    {
        String requestURI = request.getRequestURI();
        log.info("request URI: {}", requestURI);

        Controller controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(SC_NOT_FOUND);
            throw new ServletException("404 Not Found.");
        }

        controller.process(request, response);
    }
}
