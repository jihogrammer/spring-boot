package dev.jihogrammer.item.model.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemUpdateModel {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

}
