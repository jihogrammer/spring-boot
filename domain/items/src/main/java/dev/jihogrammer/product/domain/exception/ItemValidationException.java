package dev.jihogrammer.product.domain.exception;

public class ItemValidationException extends RuntimeException {

    public ItemValidationException(String message) {
        super(message);
    }

}
