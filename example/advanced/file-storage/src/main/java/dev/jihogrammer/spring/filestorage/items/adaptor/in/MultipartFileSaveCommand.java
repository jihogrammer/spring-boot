package dev.jihogrammer.spring.filestorage.items.adaptor.in;

import dev.jihogrammer.files.model.FileSaveCommand;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public record MultipartFileSaveCommand(MultipartFile delegate) implements FileSaveCommand<MultipartFile> {

    @Override
    public String filename() {
        return this.delegate.getOriginalFilename();
    }

    @Override
    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public static FileSaveCommand<MultipartFile> of(final MultipartFile multipartFile) {
        return new MultipartFileSaveCommand(multipartFile);
    }

    public static Collection<FileSaveCommand<MultipartFile>> of(final Collection<MultipartFile> files) {
        return files.stream().map(MultipartFileSaveCommand::of).toList();
    }

}
