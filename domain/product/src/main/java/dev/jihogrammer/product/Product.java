package dev.jihogrammer.product;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.exception.ProductException;
import dev.jihogrammer.product.model.*;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record Product(
    ProductId id,
    MemberId producerId,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    ProductFile descriptionFile,
    Set<ProductFile> imageFiles,
    Set<ProductType> types,
    Set<ProductRegion> regions,
    Set<ProductDeliveryType> deliveryTypes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

    public Product {
        if (isNull(id)) {
            throw new ProductException("Product id is null.");
        }
        if (isNull(producerId)) {
            throw new ProductException("Producer id is null.");
        }
        if (isNull(name) || name.isBlank()) {
            throw new ProductException("Product name is blank.");
        }
        if (isNull(price)) {
            throw new ProductException("Product price is null.");
        }
        if (price < 0) {
            throw new ProductException("Product price is not valid.");
        }
        if (nonNull(imageFiles)) {
            for (var imageFile : imageFiles) {
                if (isNull(imageFile)) {
                    throw new ProductException("Product image file is not valid.");
                }
            }
        }
        if (nonNull(types)) {
            for (var type : types) {
                if (isNull(type)) {
                    throw new ProductException("Product type is not valid.");
                }
            }
        }
        if (nonNull(regions)) {
            for (var region : regions) {
                if (isNull(region)) {
                    throw new ProductException("Product region is not valid.");
                }
            }
        }
        if (nonNull(deliveryTypes)) {
            for (var deliveryType : deliveryTypes) {
                if (isNull(deliveryType)) {
                    throw new ProductException("Product delivery type is not valid.");
                }
            }
        }
    }

}
