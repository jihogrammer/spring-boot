package dev.jihogrammer.product.domain.model;

import dev.jihogrammer.product.domain.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductType(String key, String description) {

    public ProductType {
        if (isNull(key) || key.isBlank()) {
            throw new ProductException("ProductType key is blank.");
        }
    }

}
