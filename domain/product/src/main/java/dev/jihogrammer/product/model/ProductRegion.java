package dev.jihogrammer.product.model;

import dev.jihogrammer.product.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductRegion(String value) {

    public ProductRegion {
        if (isNull(value) || value.isBlank()) {
            throw new ProductException("ProductRegion value is blank.");
        }
    }

}
