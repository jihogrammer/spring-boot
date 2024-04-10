package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductTypePort;
import dev.jihogrammer.product.domain.model.ProductType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryProductTypeAdaptor implements ProductTypePort {

    private final Map<String, ProductType> types = new ConcurrentHashMap<>();

    void save(final ProductType type) {
        this.types.put(type.key(), type);
    }

    @Override
    public Collection<ProductType> findAll() {
        return this.types.values();
    }

    @Override
    public Optional<ProductType> findByKey(final String key) {
        return Optional.ofNullable(this.types.get(key));
    }

}
