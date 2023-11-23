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
}
