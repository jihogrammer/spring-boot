package dev.jihogrammer.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {
    @GetMapping("/text")
    public String text(final Model model) {
        model.addAttribute("data", "Hello Thymeleaf!");
        model.addAttribute("unescapedData", "<strong>Hello</strong> <em>Thymeleaf!</em>");
        return "/basic/text";
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private String name;
        private Integer age;
    }

    @GetMapping("/variable")
    public String variable(final Model model) {
        User userA = new User("userA", 20);
        User userB = new User("userB", 30);

        model.addAttribute("userA", userA);
        model.addAttribute("users", List.of(userA, userB));
        model.addAttribute("userMap", Map.of(userA.getName(), userA, userB.getName(), userB));

        return "/basic/variable";
    }

    @Component("helloComponent")
    public static class HelloComponent {
        public String hello(final Object data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/objects")
    public String objects(
        final HttpSession session,
        final Model model,
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Locale locale
    ) {
        session.setAttribute("sessionData", "Hello Session!");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        model.addAttribute("locale", locale);
        return "/basic/objects";
    }

    @GetMapping("/link")
    public String link(final Model model) {
        model.addAttribute("a", "a");
        model.addAttribute("b", "b");
        return "/basic/link";
    }

    @GetMapping("/literal")
    public String literal(final Model model) {
        model.addAttribute("data", "spring");
        return "/basic/literal";
    }

    @GetMapping("/operation")
    public String operation(final Model model) {
        model.addAttribute("stringData", "spring");
        model.addAttribute("nullData", null);
        return "/basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "/basic/attribute";
    }

    @GetMapping("/each")
    public String each(final Model model) {
        model.addAttribute("users", List.of(
            new User("userA", 10),
            new User("userB", 20),
            new User("userC", 30)));
        return "/basic/each";
    }
}
