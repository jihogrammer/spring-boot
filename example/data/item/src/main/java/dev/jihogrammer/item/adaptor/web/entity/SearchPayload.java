package dev.jihogrammer.item.adaptor.web.entity;

import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import lombok.Data;

@Data
public class SearchPayload {

    private String input;

    private Integer minPrice;

    private Integer maxPrice;

    public ItemSearchCommand toCommand() {
        return new ItemSearchCommand(this.input, this.minPrice, this.maxPrice);
    }

}
