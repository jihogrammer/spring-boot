package dev.jihogrammer.logtracer.domain.exception;

public class ItemException extends RuntimeException {

    public ItemException(final String message) {
        super(message);
    }

    public ItemException(final Throwable cause) {
        super(cause);
    }

    public ItemException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
