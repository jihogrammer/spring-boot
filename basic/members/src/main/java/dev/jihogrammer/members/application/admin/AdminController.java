package dev.jihogrammer.members.application.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@ConditionalOnProperty(name = "service.members.admin.enabled")
@Slf4j
public class AdminController {

    public AdminController() {
        log.trace("Members Admin Controller is initialized.");
    }

    @GetMapping("/admin")
    public String admin() {
        return "/members/admin";
    }

}
