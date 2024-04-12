package dev.jihogrammer.member.adaptor.in.web.frontcontroller;

import java.util.Map;

public record ModelView(String viewName, Map<String, Object> model) {}
