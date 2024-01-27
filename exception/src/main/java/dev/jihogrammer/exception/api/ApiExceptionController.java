package dev.jihogrammer.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiExceptionController {
    @GetMapping("/api/members/{id}")
    public Member findMember(@PathVariable("id") String id) {
        if ("ex".equals(id)) {
            throw new RuntimeException("exception id");
        }
        return new Member(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    public static class Member {
        private String id;
        private String name;
    }
}
