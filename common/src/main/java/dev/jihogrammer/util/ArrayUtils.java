package dev.jihogrammer.util;

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

}
