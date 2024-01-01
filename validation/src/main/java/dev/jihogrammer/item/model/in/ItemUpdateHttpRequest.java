package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateHttpRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer price;
    @NotNull
    @Range(min = 1, max = 9999)
    private Integer quantity;

    public ItemUpdateCommand mapToCommand() {
        return new ItemUpdateCommand(this.id, this.name, this.price, this.quantity);
    }
}
