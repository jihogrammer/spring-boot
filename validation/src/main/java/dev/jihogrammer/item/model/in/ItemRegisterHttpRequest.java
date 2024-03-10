package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.port.in.ItemRegisterCommand;
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
    @Range(min = 1, max = 9_999)
    private Integer quantity;

    public ItemRegisterCommand mapToCommand() {
        return ItemRegisterCommand.builder()
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .build();
    }

}
