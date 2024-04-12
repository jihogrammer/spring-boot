package dev.jihogrammer.member.adaptor.in.web.entity;

import java.util.Map;

public record ModelView(String viewName, Map<String, Object> model) {}
