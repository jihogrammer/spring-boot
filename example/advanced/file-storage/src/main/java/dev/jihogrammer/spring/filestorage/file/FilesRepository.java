package dev.jihogrammer.spring.filestorage.file;

import dev.jihogrammer.spring.filestorage.items.UserFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Slf4j
class FilesRepository implements Files {

    private static final String BLANK = "";
    private static final String DOT = ".";

    private final String rootDir;

    @Override
    public String fullPath(String fileName) {
        return this.rootDir + fileName;
    }

    @Override
    public UserFile store(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String storedFileName = createStoredFileName(originalFilename);

        try {
            file.transferTo(new File(fullPath(storedFileName)));
        } catch (IOException e) {
            log.error("failed to store file", e);
            throw new RuntimeException(e);
        }

        return new UserFile(originalFilename, storedFileName);
    }

    @Override
    public Collection<UserFile> store(final Collection<MultipartFile> file) {
        List<UserFile> userFiles = new ArrayList<>();

        for (MultipartFile multipartFile : file) {
            if (multipartFile.isEmpty()) {
                continue;
            } else {
                userFiles.add(store(multipartFile));
            }
        }

        return userFiles;
    }

    private String createStoredFileName(final String originalFileName) {
        return UUID.randomUUID() + extractFileExtension(originalFileName);
    }

    private String extractFileExtension(final String fileName) {
        return isNull(fileName) ? BLANK : fileName.substring(fileName.indexOf(DOT));
    }

}
