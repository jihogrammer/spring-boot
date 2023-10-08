package dev.jihogrammer.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jihogrammer.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ResponseJSONServletTest {
    static ObjectMapper objectMapper = new ObjectMapper();
    static ResponseJSONServlet servlet = new ResponseJSONServlet(objectMapper);

    MockHttpServletRequest request;
    MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void fetchJSON() throws ServletException, IOException {
        // given
        HelloData expected = new HelloData("jihogrammer", 30);
        // when
        servlet.service(request, response);
        // then
        assertThat(response.getContentType()).isEqualTo("application/json");
        HelloData actual = objectMapper.readValue(response.getContentAsString(), HelloData.class);
        assertThat(actual.getUsername()).isEqualTo(expected.getUsername());
        assertThat(actual.getAge()).isEqualTo(expected.getAge());
    }
}