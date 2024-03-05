package dev.jihogrammer.web.frontcontroller.adapter;

import dev.jihogrammer.web.frontcontroller.model.Adapter;
import dev.jihogrammer.web.frontcontroller.model.Controller;
import dev.jihogrammer.web.frontcontroller.model.ModelView;
import dev.jihogrammer.web.frontcontroller.model.ViewNameController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> model = new HashMap<>();
        String viewName = ((ViewNameController) controller).process(model);
        return new ModelView(viewName, model);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
