package dev.jihogrammer.product.application.port.in;

import dev.jihogrammer.member.domain.model.MemberId;
import lombok.Builder;

import java.util.Set;

@Builder
public record ProductUpdateCommand(
    Long id,
    MemberId producerId,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String>regions,
    String type,
    String deliveryType
) {
}
