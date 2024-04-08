package dev.jihogrammer.product.model;

import lombok.Data;

@Data
public class ProductRegisterPayload {

    private String name;

    private Integer price;

    private Integer quantity;

}
