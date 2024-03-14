package dev.jihogrammer.annotation;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationUtilsTest {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface MyAnnotationA {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface MyAnnotationB {}

    @Test
    void containsAnyAnnotationFoo() {
        // given
        @MyAnnotationA
        class Foo {}

        // when
        var condition = AnnotationUtils.containsAnyAnnotation(Foo.class, MyAnnotationA.class, MyAnnotationB.class);

        // then
        assertThat(condition).isTrue();
    }

    @Test
    void containsAnyAnnotationBar() {
        // given
        class Bar {}

        // when
        var condition = AnnotationUtils.containsAnyAnnotation(Bar.class, MyAnnotationA.class, MyAnnotationB.class);

        // then
        assertThat(condition).isFalse();
    }

    @Test
    void containsAnyAnnotationBaz() {
        // given
        @MyAnnotationA
        @MyAnnotationB
        class Baz {}

        // when
        var condition = AnnotationUtils.containsAnyAnnotation(Baz.class, MyAnnotationA.class, MyAnnotationB.class);

        // then
        assertThat(condition).isTrue();
    }

}
