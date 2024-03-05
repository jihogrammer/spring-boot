package dev.jihogrammer.web.frontcontroller.adapter;

import dev.jihogrammer.web.frontcontroller.model.Adapter;
import dev.jihogrammer.web.frontcontroller.model.Controller;
import dev.jihogrammer.web.frontcontroller.model.ModelView;
import dev.jihogrammer.web.frontcontroller.model.ModelViewController;
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

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
