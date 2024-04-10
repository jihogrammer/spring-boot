package dev.jihogrammer.gateway.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public class GetMappingEndPointFinder {

    private static final Collection<Class<? extends Annotation>> CONTROLLER_TYPES = List.of(
            Controller.class,
            RestController.class);

    private static final Collection<Class<? extends Annotation>> MAPPING_TYPES = List.of(
            RequestMapping.class,
            GetMapping.class);

    private final ApplicationContext context;

    public Collection<String> endPoints() {
        final var result = new ArrayList<String>();

        for (Class<? extends Annotation> controllerType : CONTROLLER_TYPES) {
            for (Class<? extends Annotation> mappingType : MAPPING_TYPES) {
                final var endPoints = Arrays.stream(this.context.getBeanDefinitionNames())
                        .map(this.context::getBean)
                        .map(Object::getClass)
                        .filter(beanClass -> Arrays.stream(beanClass.getDeclaredAnnotations())
                                .map(Object::getClass)
                                .anyMatch(controllerType::isAssignableFrom))
                        .flatMap(controllerClass -> this.endPoints(controllerClass, mappingType))
                        .toList();

                result.addAll(endPoints);
            }
        }

        return result.stream().sorted().distinct().toList();
    }

    private Stream<String> endPoints(
            final Class<?> controllerClass,
            final Class<? extends Annotation> mappingType
    ) {
        final var baseURI = this.baseURIOf(controllerClass);

        return Arrays.stream(controllerClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(mappingType))
                .map(method -> method.getAnnotation(mappingType))
                .filter(annotation -> {
                    if (RequestMapping.class.isAssignableFrom(annotation.getClass())) {
                        for (RequestMethod requestMethod : ((RequestMapping) annotation).method()) {
                            if (RequestMethod.GET.equals(requestMethod)) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return true;
                    }
                })
                .map(annotation -> {
                    try {
                        var values = (String[]) mappingType.getMethod("key").invoke(annotation);
                        if (values.length == 0) {
                            return new String[] {""};
                        }
                        return values;
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Arrays::stream)
                .map(endPoint -> baseURI + endPoint);
    }

    private String baseURIOf(final Class<?> controllerClass) {
        if (controllerClass.isAnnotationPresent(RequestMapping.class)) {
            final var requestMapping = controllerClass.getAnnotation(RequestMapping.class);
            return Arrays.stream(requestMapping.value()).findFirst().orElse("");
        }
        return "";
    }

}
