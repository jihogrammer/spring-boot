package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.application.port.in.ProductRegisterCommand;
import lombok.Data;

import java.util.Set;

@Data
public class ProductRegisterPayload {

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private String type;

    private String deliveryType;

    public ProductRegisterCommand toCommand() {
        return ProductRegisterCommand.builder()
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .open(this.open)
                .regions(this.regions)
                .type(this.type)
                .deliveryType(this.deliveryType)
                .build();
    }

}
