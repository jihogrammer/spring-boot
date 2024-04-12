package dev.jihogrammer.product.adaptor.in.web.entity;

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
