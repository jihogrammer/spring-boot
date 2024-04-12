package dev.jihogrammer.filestorage.application.port.in;

import dev.jihogrammer.product.domain.Product;
import org.springframework.core.io.Resource;

public interface FileStorageUseCase {

    Product register(FileStorageRegisterCommand command);

    Product findById(Long id);

    Resource getResource(String filename);

}
