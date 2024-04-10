package dev.jihogrammer.product.domain.exception;

import org.slf4j.helpers.MessageFormatter;

public class ProductException extends RuntimeException {

    public ProductException(final String message) {
        super(message);
    }

    public static ProductException of(final String format, final Object... args) {
        return new ProductException(MessageFormatter.arrayFormat(format, args).getMessage());
    }

}
