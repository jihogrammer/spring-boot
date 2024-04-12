package dev.jihogrammer.member.adaptor.in.web.adaptor;

import dev.jihogrammer.member.adaptor.in.web.model.Adapter;
import dev.jihogrammer.member.adaptor.in.web.model.Controller;
import dev.jihogrammer.member.adaptor.in.web.controller.ModelViewController;
import dev.jihogrammer.member.adaptor.in.web.entity.ModelView;
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
