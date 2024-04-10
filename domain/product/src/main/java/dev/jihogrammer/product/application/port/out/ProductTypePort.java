package dev.jihogrammer.product.application.port.out;

import dev.jihogrammer.product.domain.model.ProductType;

import java.util.Collection;
import java.util.Optional;

public interface ProductTypePort {

    Collection<ProductType> findAll();

    Optional<ProductType> findByKey(String key);

}
