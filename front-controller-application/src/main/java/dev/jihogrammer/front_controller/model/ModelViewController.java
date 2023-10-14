package dev.jihogrammer.front_controller.model;

import java.util.Map;

public interface ModelViewController extends Controller {
    ModelView process(Map<String, String> parametersMap);
}
