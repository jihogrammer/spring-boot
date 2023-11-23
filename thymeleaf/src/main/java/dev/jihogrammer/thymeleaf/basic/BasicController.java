package dev.jihogrammer.thymeleaf.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
}
