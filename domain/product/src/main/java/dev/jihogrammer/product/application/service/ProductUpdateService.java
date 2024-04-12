package dev.jihogrammer.product.application.service;

import dev.jihogrammer.product.application.port.in.ProductUpdateCommand;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import dev.jihogrammer.product.application.port.out.*;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductId;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductPort productPort;

    private final ProductRegionPort productRegionPort;

    private final ProductTypePort productTypePort;

    private final ProductDeliveryTypePort productDeliveryTypePort;

    @Override
    public Product update(final ProductUpdateCommand command) {
        final var builder = ProductSaveCommand.builder()
                .id(new ProductId(command.id()))
                .producerId(command.producerId())
                .name(command.name())
                .price(command.price())
                .quantity(command.quantity())
                .open(command.open());

        if (isNull(command.regions()) || command.regions().isEmpty()) {
            // NO-OP
        } else {
            final var regions = this.productRegionPort.findAllByKeys(command.regions());
            builder.regions(Set.copyOf(regions));
        }

        if (isNull(command.type()) || command.type().isBlank()) {
            // NO-OP
        } else {
            this.productTypePort.findByKey(command.type())
                    .ifPresent(type -> builder.types(Set.of(type)));
        }

        if (isNull(command.deliveryType()) || command.deliveryType().isBlank()) {
            // NO-OP
        } else {
            this.productDeliveryTypePort.findByKey(command.deliveryType())
                    .ifPresent(type -> builder.deliveryTypes(Set.of(type)));
        }

        return this.productPort.save(builder.build());
    }

}
