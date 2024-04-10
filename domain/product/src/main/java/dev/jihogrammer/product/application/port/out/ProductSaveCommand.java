package dev.jihogrammer.product.application.port.out;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.domain.model.*;
import lombok.Builder;

import java.util.Set;

@Builder
public record ProductSaveCommand(
    ProductId id,
    MemberId producerId,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    ProductFile descriptionFile,
    Set<ProductFile> imageFiles,
    Set<ProductType> types,
    Set<ProductRegion> regions,
    Set<ProductDeliveryType> deliveryTypes
) {
}
