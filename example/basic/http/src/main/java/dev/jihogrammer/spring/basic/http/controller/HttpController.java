package dev.jihogrammer.spring.basic.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/http")
@Slf4j
public class HttpController {

    @RequestMapping
    @ResponseBody
    public Map<String, Object> basic(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpMethod httpMethod,
            final Locale locale,
            @RequestHeader final MultiValueMap<String, String> headers,
            @RequestHeader(value = "host") final String host,
            @CookieValue(name = "cookie", required = false) final String cookie
    ) {
        return Map.of(
                "HttpServletRequest", request.toString(),
                "HttpServletResponse", response.toString(),
                "HttpMethod", httpMethod.toString(),
                "Locale", locale,
                "@RequestHeader MultiValueMap<String, String>", headers,
                "@RequestHeader(value = \"host\") String host", host,
                "@CookieValue(name = \"cookie\", required = false) String cookie", Optional.ofNullable(cookie).orElse("<empty>"));
    }

}
