package dev.jihogrammer.product.application.service;

import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.exception.ProductException;
import dev.jihogrammer.product.domain.model.ProductId;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.out.ProductPort;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class ProductQueryService implements ProductQuery {

    private final ProductPort productPort;

    @Override
    public Collection<Product> findAll() {
        return this.productPort.findAll();
    }

    @Override
    public Product findById(Long id) {
        if (isNull(id)) {
            throw new IllegalArgumentException("id value is null.");
        }
        return this.productPort.findById(new ProductId(id))
                .orElseThrow(() -> ProductException.of("Could not find a product by id=[{}]", id));
    }

}
