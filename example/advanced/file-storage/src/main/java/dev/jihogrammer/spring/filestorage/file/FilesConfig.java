package dev.jihogrammer.spring.filestorage.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FilesConfig {

    @Bean
    public Files files(@Value("${file.dir}") final String fileDir) {
        return new FilesRepository(new File(fileDir).getAbsolutePath() + "/");
    }

}
