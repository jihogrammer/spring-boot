package dev.jihogrammer.item.model.in;

import lombok.Data;

@Data
public class ItemRegisterRequest {

    private String name;

    private Integer price;

    private Integer quantity;

}
