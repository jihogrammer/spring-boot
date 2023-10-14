package dev.jihogrammer.front_controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
    View process(HttpServletRequest request, HttpServletResponse response);
}
