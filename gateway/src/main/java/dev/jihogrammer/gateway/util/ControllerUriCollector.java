package dev.jihogrammer.gateway.util;

import dev.jihogrammer.annotation.AnnotationUtils;
import dev.jihogrammer.reflection.ClassFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

import static dev.jihogrammer.annotation.AnnotationUtils.containsAnyAnnotation;
import static dev.jihogrammer.util.ArrayUtils.containsAny;
import static dev.jihogrammer.util.ArrayUtils.isEmpty;

@Slf4j
public final class ControllerUriCollector {

    public static Collection<String> collectUris(
            final Class<?> baseClass,
            final RequestMethod requestMethod
    ) throws IOException {
        return ClassFinder.findClasses(baseClass.getPackageName()).stream()
                .filter(clazz -> AnnotationUtils.containsAnyAnnotation(clazz, Controller.class, RestController.class))
                .peek(clazz -> log.info("Found a controller: {}", clazz.getName()))
                .map(clazz -> parseUris(clazz, requestMethod))
                .flatMap(Collection::stream)
                .toList();
    }

    public static String parseUriPrefix(final Class<?> controllerClass) {
        if (containsAnyAnnotation(controllerClass, Controller.class, RestController.class)) {
            if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
                return controllerClass.getAnnotation(RequestMapping.class).value()[0];
            } else {
                return "";
            }
        }
        throw new IllegalArgumentException(controllerClass + " is not a controller.");
    }

    public static Collection<String> parseUris(final Class<?> controllerClass, final RequestMethod requestMethod) {
        var uriPrefix = parseUriPrefix(controllerClass);
        var requestMappings = findMappings(controllerClass, requestMethod);
        var methodMappings = findMappings(controllerClass, mappingClassOf(requestMethod));

        return Stream.concat(requestMappings, methodMappings)
                .map(ControllerUriCollector::getValues)
                .flatMap(values -> isEmpty(values) ? Stream.of(uriPrefix) : Arrays.stream(values).map(value -> uriPrefix + value))
                .distinct()
                .toList();
    }

    private static <T extends Annotation> Stream<T> findMappings(
            final Class<?> controllerClass,
            final Class<T> annotationType
    ) {
        return Arrays.stream(controllerClass.getDeclaredMethods())
                .map(method -> method.getAnnotation(annotationType))
                .filter(Objects::nonNull);
    }

    private static Stream<RequestMapping> findMappings(
            final Class<?> controllerClass,
            final RequestMethod requestMethod
    ) {
        return Arrays.stream(controllerClass.getDeclaredMethods())
                .map(method -> method.getAnnotation(RequestMapping.class))
                .filter(Objects::nonNull)
                .filter(annotation -> isEmpty(annotation.method()) || containsAny(annotation.method(), requestMethod));
    }

    private static Class<? extends Annotation> mappingClassOf(final RequestMethod method) {
        return switch (method) {
            case GET -> GetMapping.class;
            case POST -> PostMapping.class;
            default -> throw new NoSuchElementException("Not found matching annotation: " + method);
        };
    }

    private static String[] getValues(final Annotation annotation) {
        if (RequestMapping.class.isAssignableFrom(annotation.getClass())) {
            return ((RequestMapping) annotation).value();
        }
        if (GetMapping.class.isAssignableFrom(annotation.getClass())) {
            return ((GetMapping) annotation).value();
        }
        if (PostMapping.class.isAssignableFrom(annotation.getClass())) {
            return ((PostMapping) annotation).value();
        }
        throw new NoSuchElementException("Not found matching annotation: " + annotation);
    }

}
