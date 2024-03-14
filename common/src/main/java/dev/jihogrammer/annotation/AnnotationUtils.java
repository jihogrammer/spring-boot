package dev.jihogrammer.annotation;

import java.lang.annotation.Annotation;

public final class AnnotationUtils {

    @SafeVarargs
    public static boolean containsAnyAnnotation(final Class<?> clazz, final Class<? extends Annotation>... annotationTypes) {
        for (var annotationType : annotationTypes) {
            if (clazz.isAnnotationPresent(annotationType)) {
                return true;
            }
        }
        return false;
    }

}
