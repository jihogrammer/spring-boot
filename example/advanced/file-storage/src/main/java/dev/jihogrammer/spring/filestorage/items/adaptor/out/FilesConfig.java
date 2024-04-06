package dev.jihogrammer.spring.filestorage.items.adaptor.out;

import dev.jihogrammer.files.port.in.FileReadInteractor;
import dev.jihogrammer.files.port.in.FileReadUsage;
import dev.jihogrammer.files.port.in.FileSaveInteractor;
import dev.jihogrammer.files.port.in.FileSaveUsage;
import dev.jihogrammer.files.port.out.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Configuration
public class FilesConfig {

    @Bean
    public Files<MultipartFile> files(@Value("${file.dir}") final String fileDir) {
        return new LocalFileRepository(new File(fileDir).getAbsolutePath() + "/");
    }

    @Bean
    public FileSaveUsage<MultipartFile> fileSaveUsage(final Files<MultipartFile> files) {
        return new FileSaveInteractor<>(files);
    }

    @Bean
    public FileReadUsage fileReadUsage(final Files<MultipartFile> files) {
        return new FileReadInteractor<>(files);
    }

}
