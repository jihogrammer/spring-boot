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
    private static final String VIEW_PATH_PREFIX = "/WEB-INF/";
    private static final String VIEW_PATH_SUFFIX = ".jsp";

    private final Map<String, Controller> controllerMap;

    public FrontControllerServlet() {
        controllerMap = new HashMap<>();

        SingletonInMemoryMembers members = SingletonInMemoryMembers.getInstance();

        controllerMap.put(URI_PREFIX + "/members/new-form", new MemberFormController("new-form"));
        controllerMap.put(URI_PREFIX + "/members/save", new MemberSaveController("save-result", members));
        controllerMap.put(URI_PREFIX + "/members", new MemberListController("list", members));
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

        Map<String, String> parametersMap = makeParametersMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(parametersMap, model);
        View view = resolveViewName(viewName);

        view.render(model, request, response);
    }

    private Map<String, String> makeParametersMap(HttpServletRequest request) {
        Map<String, String> parametersMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(key -> parametersMap.put(key, request.getParameter(key)));
        return parametersMap;
    }

    private View resolveViewName(final String viewName) {
        return new View(VIEW_PATH_PREFIX + viewName + VIEW_PATH_SUFFIX);
    }
}
