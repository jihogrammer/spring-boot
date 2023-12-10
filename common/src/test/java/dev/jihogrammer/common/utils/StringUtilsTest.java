package dev.jihogrammer.common.utils;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {
    private static final Supplier<String> nullStringSupplier = () -> null;
    private static final Supplier<String> emptyStringSupplier = () -> "";

    @Test
    void isEmpty() {
        // given
        String value = "jihogrammer";
        // when
        boolean condition = StringUtils.isEmpty(value);
        // then
        assertThat(condition).isFalse();
    }

    @Test
    void nullIsEmpty() {
        // given
        String string = nullStringSupplier.get();
        // when
        boolean condition = StringUtils.isEmpty(string);
        // then
        assertThat(condition).isTrue();
    }

    @Test
    void emptyIsEmpty() {
        // given
        String string = emptyStringSupplier.get();
        // when
        boolean condition = StringUtils.isEmpty(string);
        // then
        assertThat(condition).isTrue();
    }

    @Test
    void isNotEmpty() {
        // given
        String value = "jihogrammer";
        // when
        boolean condition = StringUtils.isEmpty(value);
        // then
        assertThat(condition).isFalse();
    }

    @Test
    void nullIsNotEmpty() {
        // given
        String value = nullStringSupplier.get();
        // when
        boolean condition = StringUtils.isEmpty(value);
        // then
        assertThat(condition).isTrue();
    }

    @Test
    void emptyIsNotEmpty() {
        // given
        String value = emptyStringSupplier.get();
        // when
        boolean condition = StringUtils.isEmpty(value);
        // then
        assertThat(condition).isTrue();
    }
}
