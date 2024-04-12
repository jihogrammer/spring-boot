package dev.jihogrammer.filestorage.adaptor.in.web.entity;

import dev.jihogrammer.filestorage.application.port.in.FileStorageRegisterCommand;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@Data
public class ProductRegisterPayload {

    private String name;

    private MultipartFile descriptionFile;

    private Collection<MultipartFile> imageFiles;

    public FileStorageRegisterCommand toCommand() {
        return new FileStorageRegisterCommand(this.name, this.descriptionFile, this.imageFiles);
    }

}
