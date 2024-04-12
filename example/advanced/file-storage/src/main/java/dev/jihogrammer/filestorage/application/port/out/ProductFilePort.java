package dev.jihogrammer.filestorage.application.port.out;

import dev.jihogrammer.product.domain.model.ProductFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ProductFilePort {

    ProductFile save(MultipartFile file);

    Resource findByFilename(String filename);

}
