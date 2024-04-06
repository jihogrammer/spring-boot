package dev.jihogrammer.spring.filestorage.items.adaptor.out;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.port.out.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
class LocalFileRepository implements Files<MultipartFile> {

    private final String rootDir;

    @Override
    public String root() {
        return this.rootDir;
    }

    @Override
    public File<MultipartFile> save(final File<MultipartFile> file) {
        try (file) {
            file.delegate().transferTo(new java.io.File(this.root() + file.storedName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}
