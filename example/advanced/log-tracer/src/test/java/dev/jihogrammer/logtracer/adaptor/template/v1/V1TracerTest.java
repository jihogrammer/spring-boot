package dev.jihogrammer.logtracer.adaptor.template.v1;

import org.junit.jupiter.api.Test;

class V1TracerTest {

    @Test
    void testStartAndEnd() {
        var tracer = new V1Tracer();
        var status = tracer.start("Hello");
        tracer.end(status);
    }

    @Test
    void testStartAndFail() {
        var tracer = new V1Tracer();
        var status = tracer.start("Hello");
        tracer.fail(status, new IllegalStateException("A EXCEPTION OCCURRED"));
    }

}
