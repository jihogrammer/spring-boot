package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.domain.Product;
import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;
import dev.jihogrammer.product.application.port.in.ProductUpdateCommand;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class ProductUpdatePayload {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private String type;

    private String deliveryType;

    public static ProductUpdatePayload of(final Product product) {
        var payload = new ProductUpdatePayload();

        payload.setId(product.id().value());
        payload.setName(product.name());
        payload.setPrice(product.price());
        payload.setQuantity(product.quantity());
        payload.setOpen(product.open());
        payload.setRegions(product.regions().stream().map(ProductRegion::key).collect(Collectors.toSet()));
        payload.setType(product.types().stream().findFirst().map(ProductType::key).orElse(null));
        payload.setDeliveryType(product.deliveryTypes().stream().findFirst().map(ProductDeliveryType::key).orElse(null));

        return payload;
    }

    public ProductUpdateCommand toCommand() {
        return new ProductUpdateCommand(
                this.id,
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.type,
                this.deliveryType);
    }
}
