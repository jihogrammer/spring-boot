package dev.jihogrammer.exception.api;

import dev.jihogrammer.exception.model.ErrorResponse;
import dev.jihogrammer.exception.model.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

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

    /**
     * @see ExceptionHandlerExceptionResolver
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleException(final IllegalArgumentException e) {
        log.error("{}", e.getMessage(), e);
        return new ErrorResponse("bad", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(final UserException e) {
        log.error("{}", e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponse("user-exception", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleException(final Exception e) {
        log.error("{}", e.getMessage(), e);
        return new ErrorResponse("exception", e.getMessage());
    }

    @Data
    @AllArgsConstructor
    public static class Member {
        private String id;
        private String name;
    }
}
