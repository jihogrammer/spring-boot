package dev.jihogrammer.item.model.in;

import dev.jihogrammer.item.validation.RegisterCheck;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemRegisterHttpRequest {
    @NotBlank(groups = {RegisterCheck.class})
    private String name;
    @NotNull(groups = {RegisterCheck.class})
    @Range(min = 1_000, max = 1_000_000, groups = {RegisterCheck.class})
    private Integer price;
    @NotNull(groups = {RegisterCheck.class})
    @Range(min = 1, max = 9999, groups = {RegisterCheck.class})
    private Integer quantity;

    public ItemSaveCommand mapToCommand() {
        return new ItemSaveCommand(this.name, this.price, this.quantity);
    }
}
