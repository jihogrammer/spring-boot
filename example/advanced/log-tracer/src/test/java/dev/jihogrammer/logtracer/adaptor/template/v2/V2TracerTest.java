package dev.jihogrammer.logtracer.adaptor.template.v2;

import org.junit.jupiter.api.Test;

class V2TracerTest {

    @Test
    void testStartAndEnd() {
        var tracer = new V2Tracer();

        outer:
        {
            var status1 = tracer.start("Hello 1");
            inner:
            {
                var status2 = tracer.start(status1.traceId(), "Hello 2");
                tracer.end(status2);
            }
            tracer.end(status1);
        }
    }

    @Test
    void testStartAndFail() {
        var tracer = new V2Tracer();
        var cause = new IllegalStateException("A EXCEPTION OCCURRED");

        outer:
        {
            var status1 = tracer.start("Hello 1");
            inner:
            {
                var status2 = tracer.start(status1.traceId(), "Hello 2");
                tracer.fail(status2, cause);
            }
            tracer.fail(status1, cause);
        }
    }

}
