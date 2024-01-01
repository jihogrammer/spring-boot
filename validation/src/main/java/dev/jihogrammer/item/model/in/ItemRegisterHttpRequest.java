package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.in.ItemSaveCommand;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemRegisterHttpRequest {
    @NotBlank
    private String name;
    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer price;
    @NotNull
    @Min(1)
    @Max(9999)
    private Integer quantity;

    public ItemSaveCommand mapToCommand() {
        return new ItemSaveCommand(this.name, this.price, this.quantity);
    }
}
