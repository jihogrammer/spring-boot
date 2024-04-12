package dev.jihogrammer.filestorage.adaptor.out.file;

import dev.jihogrammer.filestorage.application.port.out.ProductFilePort;
import dev.jihogrammer.product.domain.model.ProductFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@RequiredArgsConstructor
class ProductFileAdaptor implements ProductFilePort {

    private static final String URL_RESOURCE_PREFIX = "file:";

    private final String root;

    @Override
    public ProductFile save(final MultipartFile file) {
        var storedName = NameGenerator.next(file.getOriginalFilename());

        try {
            file.transferTo(new File(this.root + storedName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ProductFile(file.getOriginalFilename(), storedName);
    }

    @Override
    public Resource findByFilename(final String filename) {
        try {
            return new UrlResource(URL_RESOURCE_PREFIX + this.root + filename);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static class NameGenerator {

        static final String BLANK = "";

        static final String DOT = ".";

        static String next(final String filename) {
            return UUID.randomUUID() + extractFileExtension(filename);
        }

        static String extractFileExtension(final String filename) {
            return filename.isBlank() ? BLANK : filename.substring(filename.lastIndexOf(DOT));
        }

    }

}
