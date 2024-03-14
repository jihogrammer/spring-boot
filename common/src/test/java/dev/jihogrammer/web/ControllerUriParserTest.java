package dev.jihogrammer.web;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerUriParserTest {

    @Test
    void parseUriPrefix() {
        // given
        var controllerClass = FooController.class;

        // when
        var uriPrefix = ControllerUriParser.parseUriPrefix(controllerClass);

        // then
        assertThat(uriPrefix).isEqualTo("/foo");
    }

    @Test
    void parseGetUris() {
        // given
        var controllerClass = FooController.class;

        // when
        var uris = ControllerUriParser.parseGetUris(controllerClass);

        // then
        assertThat(uris).contains("/foo", "/foo/bar", "/foo/get");
    }

    @Test
    void parsePostUris() {
        // given
        var controllerClass = FooController.class;

        // when
        var uris = ControllerUriParser.parsePostUris(controllerClass);

        // then
        assertThat(uris).contains("/foo", "/foo/bar", "/foo/post");
    }

    @Controller
    @RequestMapping("/foo")
    static class FooController {

        @RequestMapping
        void requestMapping() {}

        @RequestMapping(value = "/bar", method = RequestMethod.GET)
        void requestMappingGet() {}

        @RequestMapping(value = "/bar", method = RequestMethod.POST)
        void requestMappingPost() {}

        @GetMapping("/get")
        void get() {}

        @PostMapping("/post")
        void post() {}

    }

}
