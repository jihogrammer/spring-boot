package dev.jihogrammer.front_controller.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class ParameterMapper {
    public static Map<String, String> makeParametersMap(final HttpServletRequest request) {
        Map<String, String> parametersMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(key -> parametersMap.put(key, request.getParameter(key)));
        return parametersMap;
    }
}
