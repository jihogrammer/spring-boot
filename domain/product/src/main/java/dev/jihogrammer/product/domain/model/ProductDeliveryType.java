package dev.jihogrammer.product.domain.model;

import dev.jihogrammer.product.domain.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductDeliveryType(String key, String description) {

    public ProductDeliveryType {
        if (isNull(key) || key.isBlank()) {
            throw new ProductException("ProductDeliveryType key is blank.");
        }
    }

}
