package dev.jihogrammer.front_controller;

import dev.jihogrammer.front_controller.adapter.ModelViewAdapter;
import dev.jihogrammer.front_controller.adapter.ViewNameAdapter;
import dev.jihogrammer.front_controller.controller.MemberFormController;
import dev.jihogrammer.front_controller.controller.MemberListController;
import dev.jihogrammer.front_controller.controller.MemberSaveController;
import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import dev.jihogrammer.front_controller.model.ModelView;
import dev.jihogrammer.front_controller.model.View;
import dev.jihogrammer.front_controller.utils.AdapterMapper;
import dev.jihogrammer.front_controller.utils.ViewResolver;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(urlPatterns = FrontControllerServlet.URI_PREFIX + "/*")
@Slf4j
public class FrontControllerServlet extends HttpServlet {
    public static final String URI_PREFIX = "/front-controller";
    private static final String VIEW_PATH_PREFIX = "/WEB-INF/";
    private static final String VIEW_PATH_SUFFIX = ".jsp";

    private final ViewResolver viewResolver;
    private final Map<String, Controller> controllerMap;
    private final AdapterMapper adapterMapper;

    public FrontControllerServlet() {
        this.viewResolver = new ViewResolver(VIEW_PATH_PREFIX, VIEW_PATH_SUFFIX);

        this.controllerMap = new HashMap<>();
        Members members = SingletonInMemoryMemberRepository.getInstance();
        controllerMap.put(URI_PREFIX + "/members/new-form", new MemberFormController("new-form"));
        controllerMap.put(URI_PREFIX + "/members/save", new MemberSaveController("save-result", members));
        controllerMap.put(URI_PREFIX + "/members", new MemberListController("list", members));

        List<Adapter> adapters = new ArrayList<>();
        adapters.add(new ModelViewAdapter());
        adapters.add(new ViewNameAdapter());
        this.adapterMapper = new AdapterMapper(adapters);
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

        Adapter adapter = adapterMapper.map(controller);
        ModelView modelView = adapter.handle(request, response, controller);

        View view = viewResolver.resolve(modelView.viewName());
        view.render(modelView.model(), request, response);
    }
}
