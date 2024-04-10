package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductRegionPort;
import dev.jihogrammer.product.domain.model.ProductRegion;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryProductRegionAdaptor implements ProductRegionPort {

    private final Map<String, ProductRegion> regions = new ConcurrentHashMap<>();

    void save(final ProductRegion region) {
        this.regions.put(region.key(), region);
    }

    @Override
    public Collection<ProductRegion> findAll() {
        return this.regions.values();
    }

    @Override
    public Collection<ProductRegion> findAllByKeys(final Collection<String> keys) {
        return keys.stream()
                .map(this.regions::get)
                .toList();
    }

}
