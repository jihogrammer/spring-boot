package dev.jihogrammer.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.stream.Stream;

@Controller
public class HomeController {

    @RequestMapping
    public String home(final Model model) {
        model.addAttribute("basicUriPrefix", BasicController.class.getAnnotation(RequestMapping.class).value()[0]);
        model.addAttribute("basicUris", Stream.concat(fetchRequestMappingValueStream(), fetchGetMappingValueStream()).toList());

        return "/index";
    }

    private Stream<String> fetchRequestMappingValueStream() {
        return fetchAnnotationStream(RequestMapping.class)
                .map(RequestMapping::value)
                .flatMap(Arrays::stream);
    }

    private Stream<String> fetchGetMappingValueStream() {
        return fetchAnnotationStream(GetMapping.class)
                .map(GetMapping::value)
                .flatMap(Arrays::stream);
    }

    @SuppressWarnings("unchecked")
    private <T> Stream<T> fetchAnnotationStream(final Class<T> annotationClass) {
        return Arrays.stream(BasicController.class.getMethods())
                .map(AccessibleObject::getAnnotations)
                .flatMap(Arrays::stream)
                .filter(annotation -> annotationClass.isAssignableFrom(annotation.getClass()))
                .map(annotation -> (T) annotation);
    }

}
