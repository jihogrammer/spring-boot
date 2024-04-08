package dev.jihogrammer.product.model;

import dev.jihogrammer.product.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductDeliveryType(String value) {

    public ProductDeliveryType {
        if (isNull(value) || value.isBlank()) {
            throw new ProductException("ProductDeliveryType value is blank.");
        }
    }

}
