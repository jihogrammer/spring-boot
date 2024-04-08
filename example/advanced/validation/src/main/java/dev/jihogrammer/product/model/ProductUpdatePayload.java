package dev.jihogrammer.product.model;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatePayload {

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

    public static ProductUpdatePayload of(final Product product) {
        return builder()
                .id(product.id().value())
                .name(product.name())
                .price(product.price())
                .quantity(product.quantity())
                .build();
    }

    public ProductSaveCommand toCommand() {
        return ProductSaveCommand.builder()
                .producerId(new MemberId(940614))
                .id(new ProductId(this.id))
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }

}
