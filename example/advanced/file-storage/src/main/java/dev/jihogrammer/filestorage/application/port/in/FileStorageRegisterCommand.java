package dev.jihogrammer.filestorage.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public record FileStorageRegisterCommand(
    String name,
    MultipartFile file,
    Collection<MultipartFile> imageFiles
) {
}
