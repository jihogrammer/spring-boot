package dev.jihogrammer.product.application.service;

import dev.jihogrammer.product.application.port.out.ProductRegionPort;
import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;
import dev.jihogrammer.product.application.port.in.ProductDetailQuery;
import dev.jihogrammer.product.application.port.out.ProductDeliveryTypePort;
import dev.jihogrammer.product.application.port.out.ProductTypePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
class ProductDetailQueryService implements ProductDetailQuery {

    private final ProductRegionPort productRegionPort;

    private final ProductTypePort productTypePort;

    private final ProductDeliveryTypePort productDeliveryTypePort;

    @Override
    public Set<ProductRegion> findAllProductRegions() {
        return new HashSet<>(this.productRegionPort.findAll());
    }

    @Override
    public Set<ProductType> findAllProductTypes() {
        return new HashSet<>(this.productTypePort.findAll());
    }

    @Override
    public Set<ProductDeliveryType> findAllProductDeliveryTypes() {
        return new HashSet<>(this.productDeliveryTypePort.findAll());
    }

}
