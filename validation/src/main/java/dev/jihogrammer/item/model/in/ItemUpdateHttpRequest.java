package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.ItemUpdateCommand;
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
    @Range(min = 1, max = 9_999)
    private Integer quantity;

    // TODO consider 과연 도메인 객체로 변환의 책임이 HttpRequest 클래스에 있는가?
    public ItemUpdateCommand mapToCommand() {
        return ItemUpdateCommand.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .build();
    }

}
