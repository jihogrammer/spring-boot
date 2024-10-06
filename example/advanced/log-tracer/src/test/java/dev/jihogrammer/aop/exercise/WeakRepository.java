package dev.jihogrammer.aop.exercise;

import java.util.Random;

class WeakRepository {

    private static final Random RANDOM = new Random();

    @Trace
    @Retry
    String toUpperCase(final String param) {
        if (RANDOM.nextInt() % 2 == 0) {
            throw new RuntimeException("FAILED");
        }
        return param.toUpperCase();
    }

}
