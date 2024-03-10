package dev.jihogrammer.item.model.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemView {

    private final Long id;

    private final String name;

    private final Integer price;

    private final Integer quantity;

}
