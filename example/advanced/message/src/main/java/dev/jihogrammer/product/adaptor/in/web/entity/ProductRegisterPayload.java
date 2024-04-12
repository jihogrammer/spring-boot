package dev.jihogrammer.product.adaptor.in.web.entity;

import lombok.Data;

@Data
public class ProductRegisterPayload {

    private String name;

    private Integer price;

    private Integer quantity;

}
