package dev.jihogrammer.spring.filestorage.file;

import dev.jihogrammer.spring.filestorage.items.UserFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface Files {

    String fullPath(String fileName);

    UserFile store(MultipartFile file);

    Collection<UserFile> store(Collection<MultipartFile> file);

}
