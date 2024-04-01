package dev.jihogrammer.basic.http;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/http")
public class HttpController {

    @RequestMapping(method = RequestMethod.GET)
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
