package dev.jihogrammer.logtracer.adaptor.template.v3.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class FieldServiceConcurrencyTest {

    FieldService service = new FieldService();

    @Test
    void sequential() {
        // given
        log.info("given");
        var nameA = "nameA";
        var nameB = "nameB";
        var resultA = new StringBuilder();
        var resultB = new StringBuilder();
        var a = new Thread(() -> resultA.append(service.store(nameA)), "thread-a");
        var b = new Thread(() -> resultB.append(service.store(nameB)), "thread-b");

        // when
        log.info("when");
        a.start();
        sleep(2000);
        b.start();

        // then
        log.info("then");
        await(a, b);
        assertThat(resultA.toString()).isEqualTo(nameA);
        assertThat(resultB.toString()).isEqualTo(nameB);
        log.info("completed");
    }

    @Test
    void concurrency() {
        // given
        log.info("given");
        var nameA = "nameA";
        var nameB = "nameB";
        var resultA = new StringBuilder();
        var resultB = new StringBuilder();
        var a = new Thread(() -> resultA.append(service.store(nameA)), "thread-a");
        var b = new Thread(() -> resultB.append(service.store(nameB)), "thread-b");

        // when
        log.info("when");
        a.start();
        sleep(10);
        b.start();

        // then
        log.info("then");
        await(a, b);
        assertThat(resultA.toString()).isNotEqualTo(nameA);
        assertThat(resultB.toString()).isEqualTo(nameB);
        log.info("completed");
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void await(Thread... threads) {
        for (var thread : threads) {
            while (thread.isAlive()) sleep(1);
        }
    }

}
