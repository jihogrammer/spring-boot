package dev.jihogrammer.common.utils;

public final class StringUtils {
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isBlank(final String value) {
        return value == null || value.isBlank();
    }

    public static boolean hasText(final String value) {
        return !isBlank(value);
    }
}
