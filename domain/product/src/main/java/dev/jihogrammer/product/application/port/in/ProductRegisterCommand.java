package dev.jihogrammer.product.application.port.in;

import java.util.Set;

public record ProductRegisterCommand(
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String>regions,
    String type,
    String deliveryType
) {
}
