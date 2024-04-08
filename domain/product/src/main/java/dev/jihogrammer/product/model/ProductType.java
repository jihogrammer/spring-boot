package dev.jihogrammer.product.model;

import dev.jihogrammer.product.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductType(String value) {

    public ProductType {
        if (isNull(value) || value.isBlank()) {
            throw new ProductException("ProductType value is blank.");
        }
    }

}
