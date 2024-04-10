package dev.jihogrammer.product.application.port.out;

import dev.jihogrammer.product.domain.model.ProductDeliveryType;

import java.util.Collection;
import java.util.Optional;

public interface ProductDeliveryTypePort {

    Collection<ProductDeliveryType> findAll();

    Optional<ProductDeliveryType> findByKey(String key);

}
