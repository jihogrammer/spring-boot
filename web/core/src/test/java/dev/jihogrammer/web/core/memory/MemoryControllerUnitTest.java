package dev.jihogrammer.web.core.memory;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryControllerUnitTest {

    @Test
    void memory() {
        // given
        MemoryAgent memoryAgent = new ProxyMemoryAgent();
        MemoryController memoryController = new MemoryController(memoryAgent);

        // when
        Memory memory = memoryController.memory();

        // then
        assertThat(memory).isNotNull();
    }

}
