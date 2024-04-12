package dev.jihogrammer.filestorage.adaptor.in.web;

import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(name = "service.file-storage.home.enabled")
public class HomeController {

    @GetMapping
    public String home(final Model model) {
        var pages = Page.values();

        model.addAttribute("pages", pages);

        return "/index";
    }

    @Getter
    public enum Page {

        SERVLET_UPLOAD_V1("servlet upload v1", "/file-storage/servlet/v1/upload"),

        SERVLET_UPLOAD_V2("servlet upload v2", "/file-storage/servlet/v2/upload"),

        SPRING_UPLOAD("spring upload", "/file-storage/spring/upload"),

        ITEM_UPLOAD("item register", "/file-storage/products/register");

        private final String name;

        private final String uri;

        Page(final String name, final String uri) {
            this.name = name;
            this.uri = uri;
        }

    }

}
