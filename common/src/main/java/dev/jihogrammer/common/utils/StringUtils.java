package dev.jihogrammer.common.utils;

public final class StringUtils {
    public static boolean isEmpty(final String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNotEmpty(final String value) {
        return !isEmpty(value);
    }
}
