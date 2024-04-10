package dev.jihogrammer.product.application.port.in;

import dev.jihogrammer.product.domain.Product;

public interface ProductRegisterUseCase {

    Product register(ProductRegisterCommand command);

}
