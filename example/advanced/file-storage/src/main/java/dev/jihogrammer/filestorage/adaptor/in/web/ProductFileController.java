package dev.jihogrammer.filestorage.adaptor.in.web;

import dev.jihogrammer.filestorage.application.port.in.FileStorageUseCase;
import dev.jihogrammer.product.domain.model.ProductFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/file-storage/products")
@RequiredArgsConstructor
public class ProductFileController {

    private final FileStorageUseCase fileStorageUseCase;

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> images(@PathVariable("filename") final String filename) throws IOException {
        final var resource = this.fileStorageUseCase.getResource(filename);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentTypeOf(resource))
            .body(resource);
    }

    @GetMapping("/files/{productId}")
    public ResponseEntity<Resource> files(@PathVariable("productId") final Long productId) throws IOException {
        final var product = this.fileStorageUseCase.findById(productId);
        final var resource = this.fileStorageUseCase.getResource(product.descriptionFile().storedName());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentTypeOf(resource))
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionOf(product.descriptionFile()))
            .body(resource);
    }

    private String contentTypeOf(final Resource urlResource) throws IOException {
        return urlResource.getURL().openConnection().getHeaderField(HttpHeaders.CONTENT_TYPE);
    }

    private String contentDispositionOf(final ProductFile file) {
        return "attachment; filename'\"" + UriUtils.encode(file.name(), StandardCharsets.UTF_8) + "\"";
    }

}
