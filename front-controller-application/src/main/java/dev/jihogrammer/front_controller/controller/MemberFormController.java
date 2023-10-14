package dev.jihogrammer.front_controller.controller;

import dev.jihogrammer.front_controller.Controller;
import dev.jihogrammer.front_controller.View;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemberFormController implements Controller {
    private final View view;

    public MemberFormController(final View view) {
        this.view = view;
    }

    @Override
    public View process(final HttpServletRequest request, final HttpServletResponse response) {
        return view;
    }
}
