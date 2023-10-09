package dev.jihogrammer.servlet.basic.request;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class RequestBodyStringServletTest {
    static RequestBodyStringServlet servlet = new RequestBodyStringServlet();

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void sendStringMessage() throws ServletException, IOException {
        // given
        String message = "jihogrammer";
        request.setContent(message.getBytes());
        // when
        servlet.service(request, response);
        // then
        assertThat(response.getContentAsString()).isEqualTo("hello world: " + message);
    }
}