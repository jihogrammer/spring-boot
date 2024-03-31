package dev.jihogrammer.web.core.memory;

public record Memory(
        long max,
        long total,
        long free,
        long used
) {
}
