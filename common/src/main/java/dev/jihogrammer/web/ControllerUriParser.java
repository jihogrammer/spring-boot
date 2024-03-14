package dev.jihogrammer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static dev.jihogrammer.annotation.AnnotationUtils.containsAnyAnnotation;

public final class ControllerUriParser {

    public static String parseUriPrefix(final Class<?> controllerClass) {
        if (containsAnyAnnotation(controllerClass, Controller.class, RestController.class)) {
            return controllerClass.getAnnotation(RequestMapping.class).value()[0];
        }
        throw new IllegalArgumentException(controllerClass + " is not a controller");
    }

    public static Collection<String> parseGetUris(final Class<?> controllerClass) {
        var uriPrefix = parseUriPrefix(controllerClass);
        var valuesStream = Stream.concat(
                requestMappingValuesStream(controllerClass, RequestMethod.GET),
                getMappingValuesStream(controllerClass));

        return parseUris(uriPrefix, valuesStream);
    }

    public static Collection<String> parsePostUris(final Class<?> controllerClass) {
        var uriPrefix = parseUriPrefix(controllerClass);
        var valuesStream = Stream.concat(
                requestMappingValuesStream(controllerClass, RequestMethod.POST),
                postMappingValuesStream(controllerClass));

        return parseUris(uriPrefix, valuesStream);
    }

    private static Collection<String> parseUris(final String uriPrefix, final Stream<String[]> valuesStream) {
        return valuesStream
                .flatMap(values -> values.length == 0
                        ? Stream.of(uriPrefix)
                        : Arrays.stream(values).map(value -> uriPrefix + value))
                .distinct()
                .toList();
    }

    private static Stream<String[]> requestMappingValuesStream(final Class<?> controllerClass, final RequestMethod requestMethod) {
        return parseAnnotationStreamInMethods(controllerClass, RequestMapping.class)
                .filter(annotation -> annotation.method().length == 0 || Arrays.stream(annotation.method()).anyMatch(method -> method == requestMethod))
                .map(RequestMapping::value);
    }

    private static Stream<String[]> getMappingValuesStream(final Class<?> controllerClass) {
        return parseAnnotationStreamInMethods(controllerClass, GetMapping.class).map(GetMapping::value);
    }

    private static Stream<String[]> postMappingValuesStream(final Class<?> controllerClass) {
        return parseAnnotationStreamInMethods(controllerClass, PostMapping.class).map(PostMapping::value);
    }

    private static <T extends Annotation> Stream<T> parseAnnotationStreamInMethods(
            final Class<?> controllerClass,
            final Class<T> annotationType
    ) {
        return Arrays.stream(controllerClass.getDeclaredMethods())
                .map(method -> method.getAnnotation(annotationType))
                .filter(Objects::nonNull);
    }

}
