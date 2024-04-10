package dev.jihogrammer.product.domain.model;

import dev.jihogrammer.product.domain.exception.ProductException;

import static java.util.Objects.isNull;

public record ProductFile(
    String name,
    String storedName
) {

    public ProductFile {
        if (isNull(name) || name.isBlank()) {
            throw new ProductException("ProductFile name is blank.");
        }
        if (isNull(storedName) || storedName.isBlank()) {
            throw new ProductException("ProductFile storedName is blank.");
        }
    }

}
