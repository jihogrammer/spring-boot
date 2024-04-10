package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductViewModel(
    Long id,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String> regions,
    String type,
    String deliveryType
) {

    public static ProductViewModel of(final Product product) {
        return new ProductViewModel(
                product.id().value(),
                product.name(),
                product.price(),
                product.quantity(),
                product.open(),
                product.regions().stream().map(ProductRegion::key).collect(Collectors.toSet()),
                product.types().stream().findFirst().map(ProductType::key).orElse(null),
                product.deliveryTypes().stream().findFirst().map(ProductDeliveryType::key).orElse(null));
    }

    public static Collection<ProductViewModel> of(final Collection<Product> products) {
        return products.stream().map(ProductViewModel::of).toList();
    }

}
