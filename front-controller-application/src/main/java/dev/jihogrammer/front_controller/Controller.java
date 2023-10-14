package dev.jihogrammer.front_controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface Controller {
    ModelView process(Map<String, String> parametersMap);
}
