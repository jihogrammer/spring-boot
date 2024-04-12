package dev.jihogrammer.product.adaptor.in.web.mapper;

import dev.jihogrammer.member.domain.model.MemberId;
import dev.jihogrammer.product.adaptor.in.web.entity.ProductRegisterPayload;
import dev.jihogrammer.product.adaptor.in.web.entity.ProductUpdatePayload;
import dev.jihogrammer.product.adaptor.in.web.entity.ProductViewModel;
import dev.jihogrammer.product.application.port.in.ProductRegisterCommand;
import dev.jihogrammer.product.application.port.in.ProductUpdateCommand;
import dev.jihogrammer.product.domain.Product;

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

    public static ProductRegisterCommand map(final ProductRegisterPayload request) {
        return ProductRegisterCommand.builder()
                .producerId(PRODUCER_ID)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public static ProductUpdateCommand map(final ProductUpdatePayload request) {
        return ProductUpdateCommand.builder()
                .id(request.getId())
                .producerId(PRODUCER_ID)
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

}
