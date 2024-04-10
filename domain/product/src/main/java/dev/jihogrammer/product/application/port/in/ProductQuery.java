package dev.jihogrammer.product.application.port.in;

import dev.jihogrammer.product.domain.Product;

import java.util.Collection;

public interface ProductQuery {

    Collection<Product> findAll();

    Product findById(Long id);

}
