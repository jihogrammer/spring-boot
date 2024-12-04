package dev.jihogrammer.spring.boot.memory;

public class MemoryFinder {

    public Memory find() {
        return new Memory(
                Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
                Runtime.getRuntime().maxMemory());
    }

}
