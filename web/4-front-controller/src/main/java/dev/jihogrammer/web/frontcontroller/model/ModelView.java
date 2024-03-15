package dev.jihogrammer.web.frontcontroller.model;

import java.util.Map;

public record ModelView(String viewName, Map<String, Object> model) {}
