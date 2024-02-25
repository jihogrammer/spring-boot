package dev.jihogrammer.spring.filestorage.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;

    private String itemName;

    private MultipartFile userFile;

    private List<MultipartFile> userImageFiles;

}
