package dev.jihogrammer.spring.filestorage.items;

import lombok.Data;

import java.util.Collection;

@Data
public class Item {

    private Long id;

    private String itemName;

    private UserFile userFile;

    private Collection<UserFile> imageFiles;

}
