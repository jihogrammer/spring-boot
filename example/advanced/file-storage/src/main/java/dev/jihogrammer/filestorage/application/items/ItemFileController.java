package dev.jihogrammer.filestorage.application.items;

import dev.jihogrammer.fileitem.port.in.ItemFileFindUsage;
import dev.jihogrammer.files.File;
import dev.jihogrammer.items.model.ItemId;
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
@RequestMapping("/file-storage/items")
@RequiredArgsConstructor
public class ItemFileController {

    private final ItemFileFindUsage itemFileFindUsage;

    private final FileStoreUsage fileStoreUsage;

    @GetMapping("/images/{storedFilename}")
    public ResponseEntity<Resource> images(@PathVariable("storedFilename") final String storedFilename) throws IOException {
        var resource = this.fileStoreUsage.resource(storedFilename);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .body(resource);
    }

    @GetMapping("/files/{itemId}")
    public ResponseEntity<Resource> files(@PathVariable("itemId") final Long itemId) throws IOException {
        var mainFile = this.itemFileFindUsage.findMainFileByItemId(new ItemId(itemId));
        var resource = this.fileStoreUsage.resource(mainFile.storedName());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeaderValue(mainFile))
            .body(resource);
    }

    private String contentType(final Resource urlResource) throws IOException {
        return urlResource.getURL().openConnection().getHeaderField(HttpHeaders.CONTENT_TYPE);
    }

    private String contentDispositionHeaderValue(final File file) {
        return "attachment; filename'\"" + UriUtils.encode(file.name(), StandardCharsets.UTF_8) + "\"";
    }

}
