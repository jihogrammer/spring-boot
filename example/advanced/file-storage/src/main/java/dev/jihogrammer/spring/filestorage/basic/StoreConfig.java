package dev.jihogrammer.spring.filestorage.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class StoreConfig {

    @Bean
    public String fileRootDir(@Value("${file.dir}") final String fileDir) {
        return new File(fileDir).getAbsolutePath() + "/";
    }

}
