package dev.jihogrammer.product.model;

import dev.jihogrammer.product.Product;

import java.util.Collection;

public record ProductViewModel(long id, String name, int price, int quantity) {

    public static ProductViewModel of(final Product product) {
        return new ProductViewModel(
            product.id().value(),
            product.name(),
            product.price(),
            product.quantity());
    }

    public static Collection<ProductViewModel> of(final Collection<Product> items) {
        return items.stream().map(ProductViewModel::of).toList();
    }

}
