package dev.jihogrammer.exception.api;

import dev.jihogrammer.exception.model.BadRequestException;
import dev.jihogrammer.exception.model.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

@RestController
@Slf4j
public class ApiExceptionController {
    @GetMapping("/api/members/{id}")
    public Member findMember(@PathVariable("id") String id) {
        if ("ex".equals(id)) {
            throw new RuntimeException("exception id");
        }
        if ("bad".equals(id)) {
            throw new IllegalArgumentException("bad request id");
        }
        if ("user-ex".equals(id)) {
            throw new UserException("user exception");
        }
        if ("bad-req".equals(id)) {
            throw new BadRequestException();
        }
        return new Member(id, "hello " + id);
    }

    /**
     * @see ResponseStatusExceptionResolver#doResolveException
     */
    @GetMapping("/api/response-status-exception")
    public void responseStatusException() {
        // see /resources/messages.properties
        throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "error.bad", new UserException());
    }

    @Data
    @AllArgsConstructor
    public static class Member {
        private String id;
        private String name;
    }
}
