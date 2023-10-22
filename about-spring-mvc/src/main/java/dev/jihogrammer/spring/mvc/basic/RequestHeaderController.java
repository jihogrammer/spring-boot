package dev.jihogrammer.spring.mvc.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
public class RequestHeaderController {
    @RequestMapping("/headers")
    public Map<String, Object> headers(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final HttpMethod method,
            final Locale locale,
            @RequestHeader final MultiValueMap<String, String> headers,
            @RequestHeader(value = "host") final String host,
            @CookieValue(name = "cookie", required = false) String cookie
    ) {
        Map<String, Object> headerParameters = new HashMap<>();

        headerParameters.put("HttpServletRequest", request.toString());
        headerParameters.put("HttpServletResponse", response.toString());
        headerParameters.put("HttpMethod", method.toString());
        headerParameters.put("Locale", locale);
        headerParameters.put("@RequestHeader MultiValueMap<String, String>", headers);
        headerParameters.put("@RequestHeader(value = \"host\") String host", host);
        headerParameters.put("@CookieValue(name = \"cookie\", required = false) String cookie", cookie);

        return headerParameters;
    }
}
