package dev.jihogrammer.spring.typeconverter;

import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("/ip-port")
        public String ipPort(@RequestParam final InternetProtocolAndPort ipPort) {
            log.info("ip: {}", ipPort.ip());
            log.info("port: {}", ipPort.port());
            return "ok";
        }
    }

    @Controller
    @Slf4j
    public static class ConverterController {
        @GetMapping("/converter-view")
        public String converterView(final Model model) {
            model.addAttribute("number", 123);
            model.addAttribute("ipPort", new InternetProtocolAndPort("127.0.0.1", 8080));
            return "/converter-view";
        }

        @GetMapping("/converter-edit")
        public String edit(final Model model) {
            model.addAttribute("form", new Form(new InternetProtocolAndPort("8.8.8.8", 80)));
            return "/converter-edit";
        }

        @PostMapping("/converter-edit")
        public String submit(final Model model, @ModelAttribute final Form form) {
            log.info("submitted. form = {}", form);
            model.addAttribute("ipPort", form.getIpPort());
            return "/converter-view";
        }

        @Data
        @AllArgsConstructor
        public static class Form {
            private InternetProtocolAndPort ipPort;
        }
    }
}
