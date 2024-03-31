package dev.jihogrammer.time;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public final class StopWatch {

    private final String taskName;

    private final Duration duration;

    public StopWatch() {
        this("");
    }

    public StopWatch(final String taskName) {
        this.taskName = taskName;
        this.duration = new Duration();
    }

    public static StopWatch started() {
        StopWatch sw = new StopWatch();

        sw.start();

        return sw;
    }

    public static StopWatch started(final String taskName) {
        StopWatch sw = new StopWatch(taskName);

        sw.start();

        return sw;
    }

    public void start() {
        if (this.duration.isNotStarted()) {
            this.duration.start(System.currentTimeMillis());
        } else {
            log.warn("This stop watch is started already.");
        }
    }

    public long stop() {
        return this.elapsed();
    }

    public long elapsed() {
        if (this.duration.isRunning()) {
            this.duration.end(System.currentTimeMillis());
        }

        return duration.delta();
    }

    @Override
    public String toString() {
        return this.taskName + " task elapsed " + this.elapsed() + " ms";
    }

}
