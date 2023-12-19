package dev.jihogrammer.common.utils;

public final class RandomUtils {
    private static final int BASE_MODIFIER = 19940614;
    private static final int BASE_STRING_LENGTH = 14;
    private static final int SMALL_A = 'a';
    private static final int NUMBER_OF_ALPHABET = 26;

    public static int randomInt() {
        return (int) (Math.random() * BASE_MODIFIER);
    }

    public static long randomLong() {
        return (long) (Math.random() * BASE_MODIFIER);
    }

    public static String randomString() {
        return randomString((randomInt() % BASE_STRING_LENGTH) + 1);
    }

    public static String randomString(final int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("should length value greater than 0");
        }

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append((char) ((randomInt() % NUMBER_OF_ALPHABET) + SMALL_A));
        }

        return sb.toString();
    }
}
