package dev.jihogrammer.product.model;

import dev.jihogrammer.member.model.MemberId;
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
