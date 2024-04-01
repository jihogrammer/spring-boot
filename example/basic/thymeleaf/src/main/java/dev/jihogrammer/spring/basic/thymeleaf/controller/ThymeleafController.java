package dev.jihogrammer.spring.basic.thymeleaf.controller;

import dev.jihogrammer.spring.basic.thymeleaf.entity.UserData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/text")
    public String text(final Model model) {
        model.addAttribute("data", "Hello Thymeleaf!");
        model.addAttribute("unescapedData", "<strong>Hello</strong> <em>Thymeleaf!</em>");
        return "/thymeleaf/text";
    }

    @GetMapping("/variable")
    public String variable(final Model model) {
        UserData userDataA = UserData.builder().name("userA").age(10).build();
        UserData userDataB = UserData.builder().name("userB").age(20).build();

        model.addAttribute(userDataA.getName(), userDataA);
        model.addAttribute("users", List.of(userDataA, userDataB));
        model.addAttribute("userMap", Map.of(userDataA.getName(), userDataA, userDataB.getName(), userDataB));

        return "/thymeleaf/variable";
    }

    @Bean
    public Object helloComponent() {
        return new Object() {
            public String hello(String name) {
                return "Hello, " + name;
            }
        };
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

        return "/thymeleaf/objects";
    }

    @GetMapping("/link")
    public String link(final Model model) {
        model.addAttribute("a", "a");
        model.addAttribute("b", "b");

        return "/thymeleaf/link";
    }

    @GetMapping("/literal")
    public String literal(final Model model) {
        model.addAttribute("data", "spring");

        return "/thymeleaf/literal";
    }

    @GetMapping("/operation")
    public String operation(final Model model) {
        model.addAttribute("stringData", "spring");
        model.addAttribute("nullData", null);

        return "/thymeleaf/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "/thymeleaf/attribute";
    }

    @GetMapping("/each")
    public String each(final Model model) {
        model.addAttribute("users", List.of(
                UserData.builder().name("userA").age(10).build(),
                UserData.builder().name("userB").age(20).build(),
                UserData.builder().name("userC").age(30).build()));

        return "/thymeleaf/each";
    }

    @GetMapping("/condition")
    public String condition(final Model model) {
        model.addAttribute("users", List.of(
                UserData.builder().name("userA").age(10).build(),
                UserData.builder().name("userB").age(20).build(),
                UserData.builder().name("userC").age(30).build()));

        return "/thymeleaf/condition";
    }

    @GetMapping("/comment")
    public String comment(final Model model) {
        model.addAttribute("data", "comments");

        return "/thymeleaf/comment";
    }

    @GetMapping("/block")
    public String block(final Model model) {
        model.addAttribute("users", List.of(
                UserData.builder().name("userA").age(10).build(),
                UserData.builder().name("userB").age(20).build(),
                UserData.builder().name("userC").age(30).build()));

        return "/thymeleaf/block";
    }

    @GetMapping("/javascript")
    public String javascript(final Model model) {
        UserData userDataA = UserData.builder().name("userA").age(10).build();
        UserData userDataB = UserData.builder().name("userB").age(20).build();
        UserData userDataC = UserData.builder().name("userC").age(30).build();

        model.addAttribute(userDataA.getName(), userDataA);
        model.addAttribute(userDataB.getName(), userDataB);
        model.addAttribute("users", List.of(userDataA, userDataB, userDataC));

        return "/thymeleaf/javascript";
    }

}
