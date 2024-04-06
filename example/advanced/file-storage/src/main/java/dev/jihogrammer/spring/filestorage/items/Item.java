package dev.jihogrammer.spring.filestorage.items;

import dev.jihogrammer.files.File;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
public class Item {

    private Long id;

    private String itemName;

    private File<MultipartFile> userFile;

    private Collection<File<MultipartFile>> imageFiles;

}
