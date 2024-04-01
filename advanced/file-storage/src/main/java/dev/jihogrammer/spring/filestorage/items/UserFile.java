package dev.jihogrammer.spring.filestorage.items;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFile {

    private String userDefinedFileName;

    private String storedFileName;

}
