package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.web.core.ConditionalController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@ConditionalOnProperty(name = "service.members.admin.enabled")
public class AdminController implements ConditionalController {

    @GetMapping("/admin")
    public String admin() {
        return "/members/admin";
    }

}
