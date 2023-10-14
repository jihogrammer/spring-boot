package dev.jihogrammer.front_controller.adapter;

import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import dev.jihogrammer.front_controller.model.ModelView;
import dev.jihogrammer.front_controller.model.ViewNameController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static dev.jihogrammer.front_controller.utils.ParameterMapper.makeParametersMap;

public class ViewNameAdapter implements Adapter {
    @Override
    public boolean supports(final Controller controller) {
        return ViewNameController.class.isAssignableFrom(controller.getClass());
    }

    @Override
    public ModelView handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Controller controller
    ) {
        Map<String, String> parametersMap = makeParametersMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = ((ViewNameController) controller).process(parametersMap, model);
        return new ModelView(viewName, model);
    }
}
