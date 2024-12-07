package dev.jihogrammer.spring.boot.actuator.etc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LoggingLevelTest {

    static MockMvc mockMvc;

    @BeforeAll
    static void setUpClass(@Autowired final WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
                .build();
    }

    @Test
    void checkLevel() throws Exception {
        // given
        var urlTemplate = "/actuator/loggers/{package}";
        var targetPackage = "dev.jihogrammer.spring.boot.actuator.etc";
        var configRequest = MockMvcRequestBuilders
                .post(urlTemplate, targetPackage)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .content("{\"configuredLevel\": \"TRACE\"}");

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/_log/run"));
        mockMvc.perform(configRequest);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/_log/run"));
        mockMvc.perform(MockMvcRequestBuilders.get(urlTemplate, targetPackage))
                .andExpect(status().isOk())
                .andExpect(jsonPath("configuredLevel", is("TRACE")));
    }

}
