package dev.jihogrammer.product.domain.model;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.application.port.out.ProductSaveCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ProductRegisterPayload {

    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1_000, max = 1_000_000)
    private Integer price;

    @NotNull
    @Range(min = 1, max = 9_999)
    private Integer quantity;

    public ProductSaveCommand toCommand() {
        return ProductSaveCommand.builder()
                .producerId(new MemberId(940614))
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }

}
