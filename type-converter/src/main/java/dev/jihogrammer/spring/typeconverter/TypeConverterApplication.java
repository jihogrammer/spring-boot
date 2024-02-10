package dev.jihogrammer.spring.typeconverter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class TypeConverterApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TypeConverterApplication.class, args);
    }

    @RestController
    @Slf4j
    public static class HelloController {
        @GetMapping("/hello-v1")
        public String helloV1(final HttpServletRequest request) {
            String rawData = request.getParameter("data");
            Integer integerData = Integer.valueOf(rawData);
            log.info("data = {}", integerData);
            return "ok";
        }

        @GetMapping("/hello-v2")
        public String helloV2(@RequestParam final Integer data) {
            log.info("data = {}", data);
            return "ok";
        }
    }
}
