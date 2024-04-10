package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductDeliveryTypePort;
import dev.jihogrammer.product.domain.model.ProductDeliveryType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryProductProductDeliveryTypeAdaptor implements ProductDeliveryTypePort {

    private final Map<String, ProductDeliveryType> types = new ConcurrentHashMap<>();

    void save(final ProductDeliveryType type) {
        this.types.put(type.key(), type);
    }

    @Override
    public Collection<ProductDeliveryType> findAll() {
        return this.types.values();
    }

    @Override
    public Optional<ProductDeliveryType> findByKey(final String key) {
        return Optional.ofNullable(this.types.get(key));
    }

}
