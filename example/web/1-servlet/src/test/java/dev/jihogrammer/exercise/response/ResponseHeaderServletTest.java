package dev.jihogrammer.exercise.response;

import dev.jihogrammer.web.servlet.exercise.response.ResponseHeaderServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static dev.jihogrammer.web.servlet.exercise.response.ResponseHeaderServlet.*;
import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

class ResponseHeaderServletTest {
    static ResponseHeaderServlet servlet = new ResponseHeaderServlet();

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void receiveResponseWithHeaders() throws ServletException, IOException {
        // given
        String customHeaderValue = "jihogrammer";
        request.addHeader(CUSTOM_HEADER_KEY, customHeaderValue);
        // when
        servlet.service(request, response);
        // then
        assertThat(response.getHeader(CUSTOM_HEADER_KEY)).isEqualTo(customHeaderValue);
        assertThat(response.getContentType()).isEqualTo("text/plain;charset=utf-8");
        Cookie cookie = response.getCookie(CUSTOM_COOKIE_NAME);
        assertThat(cookie).isNotNull();
        assertThat(cookie.getValue()).isEqualTo(CUSTOM_COOKIE_VALUE);
        assertThat(cookie.getMaxAge()).isEqualTo(600);
        assertThat(response.getStatus()).isEqualTo(SC_FOUND);
        assertThat(response.getHeader("Location")).isEqualTo("/");
    }
}
