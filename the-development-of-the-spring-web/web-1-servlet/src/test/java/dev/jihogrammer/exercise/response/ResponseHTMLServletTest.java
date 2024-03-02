package dev.jihogrammer.exercise.response;

import dev.jihogrammer.web.servlet.exercise.response.ResponseHTMLServlet;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseHTMLServletTest {
    static ResponseHTMLServlet servlet = new ResponseHTMLServlet();

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void renderHTML() throws ServletException, IOException {
        // given
        String expectedContent = "HTML";
        // when
        servlet.service(request, response);
        // then
        assertThat(response.getContentType()).isEqualTo("text/html;charset=utf-8");
        assertThat(response.getContentAsString()).contains(expectedContent);
    }
}
