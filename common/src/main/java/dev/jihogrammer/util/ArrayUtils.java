package dev.jihogrammer.util;

import java.util.Optional;

import static java.util.Objects.nonNull;

public final class ArrayUtils {

    public static <T> boolean isEmpty(final T[] array) {
        return array.length == 0;
    }

    @SafeVarargs
    public static <T> boolean containsAny(final T[] array, final T... args) {
        for (T element : array) {
            for (T arg : args) {
                if (arg.equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> findFrom(final Class<T> type, final Object... array) {
        for (Object arg : array) {
            if (nonNull(arg) && arg.getClass().isAssignableFrom(type)) {
                return Optional.of((T) arg);
            }
        }
        return Optional.empty();
    }

}
