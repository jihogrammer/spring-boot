package dev.jihogrammer.spring.exception.api;

import dev.jihogrammer.spring.exception.model.BadRequestException;
import dev.jihogrammer.spring.exception.model.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

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

    /**
     * @see DefaultHandlerExceptionResolver#doResolveException
     * @see DefaultHandlerExceptionResolver#handleTypeMismatch
     * @see TypeMismatchException
     * @see DefaultHandlerExceptionResolver#handleMissingServletRequestParameter
     * @see MissingServletRequestParameterException
     */
    @GetMapping("/api/default-handler-exception")
    public String defaultHandlerException(@RequestParam int param) {
        return "ok";
    }

    @Data
    @AllArgsConstructor
    public static class Member {
        private String id;
        private String name;
    }
}
