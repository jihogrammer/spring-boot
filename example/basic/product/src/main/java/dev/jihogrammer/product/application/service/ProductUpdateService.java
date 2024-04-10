package dev.jihogrammer.product.application.service;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.application.port.out.*;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductId;
import dev.jihogrammer.product.application.port.in.ProductUpdateCommand;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import dev.jihogrammer.product.application.port.out.ProductTypePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class ProductUpdateService implements ProductUpdateUseCase {

    private final ProductPort productPort;

    private final MemberId producerId;

    private final ProductRegionPort productRegionPort;

    private final ProductTypePort productTypePort;

    private final ProductDeliveryTypePort productDeliveryTypePort;

    @Override
    public Product update(final ProductUpdateCommand command) {
        final var builder = ProductSaveCommand.builder()
                .id(new ProductId(command.id()))
                .producerId(this.producerId)
                .name(command.name())
                .price(command.price())
                .quantity(command.quantity())
                .open(command.open())
                .regions(new HashSet<>(this.productRegionPort.findAllByKeys(command.regions())));

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
