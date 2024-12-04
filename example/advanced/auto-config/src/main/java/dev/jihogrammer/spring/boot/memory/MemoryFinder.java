package dev.jihogrammer.spring.boot.memory;

class MemoryFinder {

    Memory find() {
        return new Memory(
                Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(),
                Runtime.getRuntime().maxMemory());
    }

}
