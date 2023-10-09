package dev.jihogrammer.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(urlPatterns = "/request-parameter")
@Slf4j
public class RequestParameterServlet extends HttpServlet {
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        printSingleValueParameters(request);
        printMultiValueParameters(request);
    }

    private void printSingleValueParameters(final HttpServletRequest request) {
        log.info("--- SINGLE VALUE PARAMETERS ---");
        request.getParameterNames().asIterator()
                .forEachRemaining(name -> log.info("param {}: {}", name, request.getParameter(name)));
    }

    private void printMultiValueParameters(final HttpServletRequest request) {
        log.info("--- MULTI VALUES PARAMETERS ---");
        request.getParameterNames().asIterator()
                .forEachRemaining(name -> {
                    String[] values = request.getParameterValues(name);
                    if (values.length > 1) {
                        log.info("{}: {}", name, values);
                    }
                });
    }
}
