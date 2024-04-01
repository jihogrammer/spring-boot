package dev.jihogrammer.spring.filestorage;

import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class FileStorageApplication {
    public static void main(final String[] args) {
        SpringApplication.run(FileStorageApplication.class, args);
    }

    @Controller
    public static class HomeController {
        @GetMapping
        public String home(final Model model) {
            model.addAttribute("pages", Page.values());
            return "/index";
        }

        @Getter
        public enum Page {
            SERVLET_UPLOAD_V1("servlet upload v1", "/servlet/v1/upload"),
            SERVLET_UPLOAD_V2("servlet upload v2", "/servlet/v2/upload"),
            SPRING_UPLOAD("spring upload", "/spring/upload"),
            ITEM_UPLOAD("item register", "/items/register");

            private final String name;
            private final String uri;

            Page(final String name, final String uri) {
                this.name = name;
                this.uri = uri;
            }
        }
    }
}
