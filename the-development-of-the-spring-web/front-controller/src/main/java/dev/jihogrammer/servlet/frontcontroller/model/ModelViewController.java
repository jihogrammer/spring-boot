package dev.jihogrammer.front_controller.model;

import jakarta.servlet.http.HttpServletRequest;

public interface ModelViewController extends Controller {

    ModelView process(HttpServletRequest request);

}
