package dev.jihogrammer.product.domain.model;

import dev.jihogrammer.product.domain.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductRegion(String key, String description) {

    public ProductRegion {
        if (isNull(key) || key.isBlank()) {
            throw new ProductException("ProductRegion key is blank.");
        }
    }

}
