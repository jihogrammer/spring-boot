package dev.jihogrammer.filestorage.application.items.model;

import dev.jihogrammer.product.domain.model.ItemRegisterCommand;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
public class ItemRegisterPayload {

    private String name;

    private MultipartFile file;

    private Collection<MultipartFile> imageFiles;

    public ItemRegisterCommand toItemRegisterCommand() {
        return ItemRegisterCommand.builder().name(this.name).build();
    }

}
