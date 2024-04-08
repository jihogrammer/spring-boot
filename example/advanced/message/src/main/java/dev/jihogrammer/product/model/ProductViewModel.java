package dev.jihogrammer.product.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductViewModel {

    private final Long id;

    private final String name;

    private final Integer price;

    private final Integer quantity;

}
