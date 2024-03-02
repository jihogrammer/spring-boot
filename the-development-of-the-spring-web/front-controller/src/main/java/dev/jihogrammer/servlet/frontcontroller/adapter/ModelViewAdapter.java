package dev.jihogrammer.front_controller.adapter;

import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import dev.jihogrammer.front_controller.model.ModelView;
import dev.jihogrammer.front_controller.model.ModelViewController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        return ((ModelViewController) controller).process(request);
    }
}
