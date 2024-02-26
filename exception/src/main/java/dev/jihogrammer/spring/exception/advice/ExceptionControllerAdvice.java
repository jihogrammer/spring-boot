package dev.jihogrammer.spring.exception.advice;

import dev.jihogrammer.spring.exception.model.ErrorResponse;
import dev.jihogrammer.spring.exception.model.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
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
}
