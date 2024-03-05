package dev.jihogrammer.web.frontcontroller.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Adapter {

    boolean supports(Controller controller);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Controller controller)
            throws ServletException, IOException;

}
