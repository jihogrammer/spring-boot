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
        return new ProductRegisterCommand(
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.type,
                this.deliveryType);
    }

}
