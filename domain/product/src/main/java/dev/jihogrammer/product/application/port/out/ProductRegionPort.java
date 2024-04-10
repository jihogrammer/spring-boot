package dev.jihogrammer.product.application.port.out;

import dev.jihogrammer.product.domain.model.ProductRegion;

import java.util.Arrays;
import java.util.Collection;

public interface ProductRegionPort {

    Collection<ProductRegion> findAll();

    Collection<ProductRegion> findAllByKeys(Collection<String> keys);

    default Collection<ProductRegion> findAllByKeys(final String... keys) {
        return this.findAllByKeys(Arrays.asList(keys));
    }

}
