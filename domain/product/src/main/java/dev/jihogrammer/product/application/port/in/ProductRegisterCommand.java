package dev.jihogrammer.product.application.port.in;

import dev.jihogrammer.member.domain.model.MemberId;
import dev.jihogrammer.product.domain.model.ProductFile;
import lombok.Builder;

import java.util.Set;

@Builder
public record ProductRegisterCommand(
    MemberId producerId,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    ProductFile descriptionFile,
    Set<ProductFile> imageFiles,
    Set<String>regions,
    String type,
    String deliveryType
) {
}
