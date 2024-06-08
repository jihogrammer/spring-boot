package dev.jihogrammer.logtracer.adaptor.v3;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.application.service.ThreadLocalTracer;
import org.junit.jupiter.api.Test;

class ThreadLocalTracerTest {

    Tracer tracer = new ThreadLocalTracer();

    @Test
    void success2Level() {
        level_1:
        {
            var status1 = tracer.start("level_1");
            level_2:
            {
                var status2 = tracer.start("level_2");
                tracer.end(status2);
            }
            tracer.end(status1);
        }
    }

    @Test
    void fail2Level() {
        var cause = new UnsupportedOperationException("LOVE");

        level_1:
        {
            var status1 = tracer.start("level_1");
            level_2:
            {
                var status2 = tracer.start("level_2");
                tracer.fail(status2, cause);
            }
            tracer.fail(status1, cause);
        }
    }

}
