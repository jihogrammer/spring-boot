package dev.jihogrammer.front_controller.adapter;

import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import dev.jihogrammer.front_controller.model.ModelView;
import dev.jihogrammer.front_controller.model.ModelViewController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import static dev.jihogrammer.front_controller.utils.ParameterMapper.makeParametersMap;

public class ModelViewAdapter implements Adapter {
    @Override
    public boolean supports(Controller controller) {
        return ModelViewController.class.isAssignableFrom(controller.getClass());
    }

    @Override
    public ModelView handle(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Controller controller
    ) {
        Map<String, String> parametersMap = makeParametersMap(request);
        return ((ModelViewController) controller).process(parametersMap);
    }
}
