package dev.jihogrammer.item.model.in;

import dev.jihogrammer.item.validation.UpdateCheck;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateHttpRequest {
    @NotNull(groups = {UpdateCheck.class})
    private Long id;
    @NotBlank(groups = {UpdateCheck.class})
    private String name;
    @NotNull(groups = {UpdateCheck.class})
    @Range(min = 1_000, max = 1_000_000, groups = {UpdateCheck.class})
    private Integer price;
    @NotNull(groups = {UpdateCheck.class})
    @Range(min = 1, max = 9999, groups = {UpdateCheck.class})
    private Integer quantity;

    public ItemUpdateCommand mapToCommand() {
        return new ItemUpdateCommand(this.id, this.name, this.price, this.quantity);
    }
}
