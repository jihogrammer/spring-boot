package dev.jihogrammer.product.application.port.in;

import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;

import java.util.Set;

public interface ProductDetailQuery {

    Set<ProductRegion> findAllProductRegions();

    Set<ProductType> findAllProductTypes();

    Set<ProductDeliveryType> findAllProductDeliveryTypes();

}
