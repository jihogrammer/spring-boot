package dev.jihogrammer.front_controller.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignUpRequestParameterParser {

    private final String nameParameterKey;

    private final String ageParameterKey;

    public SignUpRequestParameterParser(final String nameParameterKey, final String ageParameterKey) {
        log.info("parameters: name={}, age={}", nameParameterKey, ageParameterKey);
        this.nameParameterKey = nameParameterKey;
        this.ageParameterKey = ageParameterKey;
    }

    public String parseName(final HttpServletRequest request) {
        return request.getParameter(this.nameParameterKey);
    }

    public int parseAge(final HttpServletRequest request) {
        return Integer.parseInt(request.getParameter(this.ageParameterKey));
    }

}
