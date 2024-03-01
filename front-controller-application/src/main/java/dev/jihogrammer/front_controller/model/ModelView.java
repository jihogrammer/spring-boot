package dev.jihogrammer.front_controller.model;

import java.util.Map;

public record ModelView(String viewName, Map<String, Object> model) {}
