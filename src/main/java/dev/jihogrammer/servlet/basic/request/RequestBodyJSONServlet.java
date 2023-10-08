package dev.jihogrammer.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = RequestBodyJSONServlet.URI)
@RequiredArgsConstructor
@Slf4j
public class RequestBodyJSONServlet extends HttpServlet {
    public static final String URI = "/request-body-json";

    private final ObjectMapper objectMapper;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String content = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("request in: {}", content);

        HelloData helloData = objectMapper.readValue(content, HelloData.class);
        response.getWriter().write(helloData.getUsername() + ":" + helloData.getAge());
    }
}
