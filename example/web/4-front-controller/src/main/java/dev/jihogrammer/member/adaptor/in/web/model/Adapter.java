package dev.jihogrammer.member.adaptor.in.web.adaptor;

import dev.jihogrammer.member.adaptor.in.web.entity.ModelView;
import dev.jihogrammer.member.adaptor.in.web.controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Adapter {

    boolean supports(Controller controller);

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Controller controller)
            throws ServletException, IOException;

}
