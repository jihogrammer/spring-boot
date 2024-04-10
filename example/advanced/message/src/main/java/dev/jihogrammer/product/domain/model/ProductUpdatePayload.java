package dev.jihogrammer.product.domain.model;

import lombok.Data;

@Data
public class ProductUpdatePayload {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

}
