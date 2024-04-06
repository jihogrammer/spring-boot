package dev.jihogrammer.spring.filestorage.items;

import dev.jihogrammer.files.port.in.FileReadUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/file-storage/items")
@RequiredArgsConstructor
public class ItemFileController {

    private static final String URL_RESOURCE_PREFIX = "file:";

    private final Items items;

    private final FileReadUsage fileReadUsage;

    @GetMapping("/images/{storedFilename}")
    public ResponseEntity<Resource> images(@PathVariable("storedFilename") final String storedFilename) throws IOException {
        var path = this.fileReadUsage.absolutePathOf(storedFilename);
        var resource = this.resourceOf(path);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .body(resource);
    }

    @GetMapping("/files/{itemId}")
    public ResponseEntity<Resource> files(@PathVariable("itemId") final Long itemId) throws IOException {
        var item = this.items.findById(itemId);
        var path = this.fileReadUsage.absolutePathOf(item.getUserFile().storedName());
        var resource = this.resourceOf(path);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeaderValue(item))
            .body(resource);
    }

    private Resource resourceOf(final String path) throws MalformedURLException {
        return new UrlResource(URL_RESOURCE_PREFIX + path);
    }

    private String contentType(final Resource urlResource) throws IOException {
        return urlResource.getURL().openConnection().getHeaderField(HttpHeaders.CONTENT_TYPE);
    }

    private String contentDispositionHeaderValue(final Item item) {
        return "attachment; filename'\"" + UriUtils.encode(item.getUserFile().name(), StandardCharsets.UTF_8) + "\"";
    }

}
