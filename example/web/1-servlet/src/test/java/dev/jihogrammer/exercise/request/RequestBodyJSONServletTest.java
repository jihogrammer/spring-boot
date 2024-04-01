package dev.jihogrammer.exercise.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.web.servlet.exercise.HelloData;
import dev.jihogrammer.web.servlet.exercise.request.RequestBodyJSONServlet;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class RequestBodyJSONServletTest {
    static ObjectMapper objectMapper = new ObjectMapper();
    static RequestBodyJSONServlet servlet = new RequestBodyJSONServlet(objectMapper);

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void sendJsonBody() throws ServletException, IOException {
        // given
        HelloData helloData = new HelloData();
        helloData.setName("jihogrammer");
        helloData.setAge(30);
        request.setContent(objectMapper.writeValueAsBytes(helloData));
        // when
        servlet.service(request, response);
        // then
        assertThat(response.getContentAsString()).isEqualTo(helloData.getName() + ":" + helloData.getAge());
    }
}
