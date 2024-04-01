package dev.jihogrammer.spring.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

/**
 * @see ResponseStatusExceptionResolver#applyStatusAndReason
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "error.bad") // see resources/messages.properties
public class BadRequestException extends RuntimeException {}
