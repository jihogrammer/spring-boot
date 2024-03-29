package dev.jihogrammer.spring.exception.api;

import dev.jihogrammer.spring.exception.model.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2")
@Slf4j
public class ApiExceptionHandlerController {
    @GetMapping("/api/members/{id}")
    public Member findMember(@PathVariable("id") String id) {
        if ("ex".equals(id)) {
            throw new RuntimeException("unknown server error");
        }
        if ("bad".equals(id)) {
            throw new IllegalArgumentException("bad request id");
        }
        if ("user-ex".equals(id)) {
            throw new UserException("user exception");
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
