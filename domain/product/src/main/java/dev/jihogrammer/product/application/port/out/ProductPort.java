package dev.jihogrammer.product.application.port.out;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductId;

import java.util.Collection;
import java.util.Optional;

public interface ProductPort {

    Product save(ProductSaveCommand command);

    Collection<Product> findAll();

    Optional<Product> findById(ProductId id);

    Collection<Product> findByProducerId(MemberId producerId);

}
