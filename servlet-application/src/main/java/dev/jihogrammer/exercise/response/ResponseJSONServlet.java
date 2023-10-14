package dev.jihogrammer.exercise.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.exercise.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@WebServlet(urlPatterns = "/response-json")
@RequiredArgsConstructor
public class ResponseJSONServlet extends HttpServlet {
    private final ObjectMapper objectMapper;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        HelloData helloData = new HelloData("jihogrammer", 30);
        String content = objectMapper.writeValueAsString(helloData);
        response.getOutputStream().write(content.getBytes());
    }
}
