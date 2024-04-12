package dev.jihogrammer.product.application.service;

import dev.jihogrammer.member.domain.model.MemberId;
import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.application.port.out.ProductSaveCommand;
import dev.jihogrammer.product.application.port.in.ProductRegisterCommand;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import dev.jihogrammer.product.application.port.out.ProductDeliveryTypePort;
import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.application.port.out.ProductRegionPort;
import dev.jihogrammer.product.application.port.out.ProductTypePort;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
class ProductRegisterService implements ProductRegisterUseCase {

    private final ProductPort productPort;

    private final MemberId producerId;

    private final ProductRegionPort productRegionPort;

    private final ProductTypePort productTypePort;

    private final ProductDeliveryTypePort productDeliveryTypePort;

    @Override
    public Product register(final ProductRegisterCommand command) {
        final var builder = ProductSaveCommand.builder()
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
