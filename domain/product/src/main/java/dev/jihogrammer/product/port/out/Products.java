package dev.jihogrammer.product.port.out;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.Product;
import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.model.ProductSaveCommand;

import java.util.Collection;
import java.util.Optional;

public interface Products {

    Product save(ProductSaveCommand command);

    Collection<Product> findAll();

    Optional<Product> findById(ProductId id);

    Collection<Product> findByProducerId(MemberId producerId);

}
