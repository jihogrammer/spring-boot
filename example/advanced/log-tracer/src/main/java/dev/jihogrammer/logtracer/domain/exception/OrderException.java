package dev.jihogrammer.logtracer.domain.exception;

public class OrderException extends RuntimeException {

    public OrderException(final String message) {
        super(message);
    }

    public OrderException(final Throwable cause) {
        super(cause);
    }

    public OrderException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
