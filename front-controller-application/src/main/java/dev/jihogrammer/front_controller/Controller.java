package dev.jihogrammer.front_controller;

import java.util.Map;

public interface Controller {
    String process(Map<String, String> parametersMap, Map<String, Object> model);
}
