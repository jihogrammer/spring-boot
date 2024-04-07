package dev.jihogrammer.filestorage.infrastructure.adaptor.in;

import dev.jihogrammer.fileitem.port.in.ItemFileFindInteractor;
import dev.jihogrammer.fileitem.port.in.ItemFileFindUsage;
import dev.jihogrammer.fileitem.port.out.ItemFiles;
import dev.jihogrammer.files.port.in.FileReadInteractor;
import dev.jihogrammer.files.port.in.FileReadUsage;
import dev.jihogrammer.files.port.out.Files;
import dev.jihogrammer.filestorage.application.items.FileStoreUsage;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageAdaptorInConfig {

    @Bean
    public FileStoreUsage fileStoreUsage(
        final String fileRootDir,
        final FileReadUsage fileReadUsage,
        final Files files,
        final ItemFiles itemFiles
    ) {
        return new FileStoreService(fileRootDir, fileReadUsage, files, itemFiles);
    }

    @Bean
    public ItemFileFindUsage itemFileFindUsage(
        final Items items,
        final Files files,
        final ItemFiles itemFiles
    ) {
        return new ItemFileFindInteractor(items, files, itemFiles);
    }

    @Bean
    public FileReadUsage fileReadUsage(final Files files) {
        return new FileReadInteractor(files);
    }

}
