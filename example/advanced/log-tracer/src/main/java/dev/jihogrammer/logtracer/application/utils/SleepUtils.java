package dev.jihogrammer.logtracer.application.utils;

public final class SleepUtils {

    public static void sleep() {
        sleep(200 + (long) (Math.random() * 800));
    }

    public static void sleep(final Integer ms) {
        sleep((long) ms);
    }

    public static void sleep(final Long ms) {
        if (ms == null) {
            sleep();
        } else {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
