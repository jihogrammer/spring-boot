package dev.jihogrammer.product;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.model.ProductRegisterPayload;
import dev.jihogrammer.product.model.ProductUpdatePayload;
import dev.jihogrammer.product.model.ProductViewModel;
import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.model.ProductSaveCommand;

import java.util.Collection;

public class ProductEntityMapper {

    private static final MemberId PRODUCER_ID = new MemberId(940614);

    public static ProductViewModel map(final Product product) {
        return ProductViewModel.builder()
                .id(product.id().value())
                .name(product.name())
                .price(product.price())
                .quantity(product.quantity())
                .build();
    }

    public static Collection<ProductViewModel> map(final Collection<Product> products) {
        return products.stream().map(ProductEntityMapper::map).toList();
    }

    public static ProductSaveCommand map(final ProductRegisterPayload request) {
        return ProductSaveCommand.builder()
                .producerId(PRODUCER_ID)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public static ProductSaveCommand map(final ProductUpdatePayload request) {
        return ProductSaveCommand.builder()
                .id(new ProductId(request.getId()))
                .producerId(PRODUCER_ID)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

}
