package dev.jihogrammer.filestorage.infrastructure.adaptor.out;

import dev.jihogrammer.fileitem.port.out.InMemoryItemFileRepository;
import dev.jihogrammer.fileitem.port.out.ItemFiles;
import dev.jihogrammer.files.port.out.Files;
import dev.jihogrammer.files.port.out.InMemoryFileRepository;
import dev.jihogrammer.items.port.out.InMemoryItemRepository;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileStorageAdaptorOutConfig {

    @Bean
    public String fileRootDir(@Value("${file.dir}") final String fileDir) {
        return new File(fileDir).getAbsolutePath() + "/";
    }

    @Bean
    public Items items() {
        return new InMemoryItemRepository();
    }

    @Bean
    public Files files(final String fileRootDir) {
        return new InMemoryFileRepository(fileRootDir);
    }

    @Bean
    public ItemFiles itemFiles() {
        return new InMemoryItemFileRepository();
    }

}
