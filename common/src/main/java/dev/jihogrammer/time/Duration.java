package dev.jihogrammer.time;


import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Duration {

    private Long start;

    private Long end;

    public void start(final long start) {
        this.start = start;
    }

    public void end(final long end) {
        this.end = end;
    }

    public boolean isStarted() {
        return nonNull(this.start);
    }

    public boolean isNotStarted() {
        return !this.isStarted();
    }

    public boolean isRunning() {
        return isNull(this.end);
    }

    public long delta() {
        if (isNull(this.start)) {
            throw new UnsupportedOperationException("start is null.");
        }
        if (isNull(this.end)) {
            throw new UnsupportedOperationException("end is null.");
        }
        return this.end - this.start;
    }

}
