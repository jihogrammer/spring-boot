package dev.jihogrammer.member.adaptor.in.web.frontcontroller;

import dev.jihogrammer.member.adaptor.in.web.model.Adapter;
import dev.jihogrammer.member.adaptor.in.web.model.Controller;
import dev.jihogrammer.member.adaptor.in.web.model.ViewNameController;
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
