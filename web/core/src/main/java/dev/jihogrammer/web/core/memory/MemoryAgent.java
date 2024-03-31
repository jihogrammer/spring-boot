package dev.jihogrammer.web.core.memory;

public class MemoryAgent {

    public Memory check() {
        final long max = Runtime.getRuntime().maxMemory();
        final long total = Runtime.getRuntime().totalMemory();
        final long free = Runtime.getRuntime().freeMemory();
        final long used = total - free;

        return new Memory(max, total, free, used);
    }

}
