package dev.jihogrammer.web.springmvc.controller;

import dev.jihogrammer.member.application.port.out.MemberPort;
import dev.jihogrammer.member.application.port.out.MemberSaveCommand;
import dev.jihogrammer.web.springmvc.model.MemberView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
public class SpringControllerConfig {

    @Controller
    public static class SpringHomeController {

        @GetMapping
        public String home() {
            return "redirect:" + URI.LEGACY_CONTROLLER_HOME;
        }

    }

    @Slf4j
    @Controller
    public static class SpringMemberController {

        private final MemberPort memberPort;

        private final String signUpGetViewName;

        private final String signUpPostViewName;

        private final String membersGetViewName;

        public SpringMemberController(
            final MemberPort memberPort,
            @Value("${service.member.sign-up.get-view-username}") final String signUpGetViewName,
            @Value("${service.member.sign-up.post-view-username}") final String signUpPostViewName,
            @Value("${service.member.members.get-view-username}") final String membersGetViewName
        ) {
            this.memberPort = memberPort;
            this.signUpGetViewName = signUpGetViewName;
            this.signUpPostViewName = signUpPostViewName;
            this.membersGetViewName = membersGetViewName;
        }

        @GetMapping(URI.SPRING_MEMBER_SIGN_UP)
        public String signUpForm() {
            log.info("REQUEST SPRING SIGN-UP FORM");

            return this.signUpGetViewName;
        }

        @PostMapping(URI.SPRING_MEMBER_SIGN_UP)
        public String signUp(
            @RequestParam("username") final String name,
            @RequestParam("age") final int age,
            final Model model
        ) {
            log.info("REQUEST SPRING SIGN-UP - username={}, age={}", name, age);

            var command = MemberSaveCommand.builder().name(name).age(age).build();
            var registeredMember = this.memberPort.save(command);
            var newMember = new MemberView(registeredMember);

            model.addAttribute("newMember", newMember);
            return this.signUpPostViewName;
        }

        @GetMapping(URI.SPRING_MEMBERS)
        public String members(final Model model) {
            log.info("REQUEST SPRING MEMBERS");

            var members = this.memberPort.findAll();
            var memberViews = members.stream().map(MemberView::new).toList();

            model.addAttribute("members", memberViews);
            return this.membersGetViewName;
        }

    }

}
