package dev.jihogrammer.filestorage.adaptor.in.web.entity;

import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductFile;

import java.util.Collection;

public record ProductViewModel(
    Long id,
    String name,
    ProductFile descriptionFile,
    Collection<ProductFile> imageFiles
) {

    public static ProductViewModel of(final Product product) {
        return new ProductViewModel(
            product.id().value(),
            product.name(),
            product.descriptionFile(),
            product.imageFiles());
    }

}
