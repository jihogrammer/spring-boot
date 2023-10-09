package dev.jihogrammer.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/request-json")
@RequiredArgsConstructor
public class RequestBodyJSONServlet extends HttpServlet {
    private final ObjectMapper objectMapper;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String content = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        HelloData helloData = objectMapper.readValue(content, HelloData.class);
        response.getWriter().write(helloData.getUsername() + ":" + helloData.getAge());
    }
}
