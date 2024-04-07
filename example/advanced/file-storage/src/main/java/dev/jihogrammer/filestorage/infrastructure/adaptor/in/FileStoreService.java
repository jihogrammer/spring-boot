package dev.jihogrammer.filestorage.infrastructure.adaptor.in;

import dev.jihogrammer.fileitem.ItemFile;
import dev.jihogrammer.fileitem.model.ItemFileType;
import dev.jihogrammer.fileitem.port.out.ItemFiles;
import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileSaveCommand;
import dev.jihogrammer.files.port.in.FileReadUsage;
import dev.jihogrammer.files.port.out.Files;
import dev.jihogrammer.filestorage.application.items.FileStoreUsage;
import dev.jihogrammer.filestorage.application.items.model.ItemRegisterPayload;
import dev.jihogrammer.items.model.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RequiredArgsConstructor
class FileStoreService implements FileStoreUsage {

    private static final String URL_RESOURCE_PREFIX = "file:";

    private final String fileBaseDir;

    private final FileReadUsage fileReadUsage;

    private final Files files;

    private final ItemFiles itemFiles;

    @Override
    public void saveFiles(final ItemId id, final ItemRegisterPayload payload) {
        var mainFile = this.saveFile(payload.getFile());
        var itemFile = new ItemFile(id, mainFile.id(), ItemFileType.MAIN);
        this.itemFiles.save(itemFile);

        payload.getImageFiles().stream()
            .map(this::saveFile)
            .map(file -> new ItemFile(id, file.id(), ItemFileType.SUB))
            .forEach(this.itemFiles::save);
    }

    @Override
    public Resource resource(final String storedFilename) {
        var path = this.fileReadUsage.absolutePathOf(storedFilename);

        try {
            return new UrlResource(URL_RESOURCE_PREFIX + path);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File saveFile(final MultipartFile multipartFile) {
        var command = new FileSaveCommand(multipartFile.getOriginalFilename());
        var file = this.files.save(command);

        try {
            var path = this.fileBaseDir + file.storedName();
            multipartFile.transferTo(new java.io.File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

}
