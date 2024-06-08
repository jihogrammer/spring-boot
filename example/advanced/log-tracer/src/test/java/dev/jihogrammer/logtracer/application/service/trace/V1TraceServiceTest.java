package dev.jihogrammer.logtracer.application.service.trace;

import org.junit.jupiter.api.Test;

class V1TraceServiceTest {

    @Test
    void testStartAndEnd() {
        var tracer = new V1TraceService();
        var status = tracer.start("Hello");
        tracer.end(status);
    }

    @Test
    void testStartAndFail() {
        var tracer = new V1TraceService();
        var status = tracer.start("Hello");
        tracer.fail(status, new IllegalStateException("A EXCEPTION OCCURRED"));
    }

}
