package dev.jihogrammer.filestorage.adaptor.out.file;

import dev.jihogrammer.filestorage.application.port.out.ProductFilePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileStorageAdaptorOutConfig {

    @Bean
    public String root(@Value("${file.dir}") final String fileDir) {
        return new File(fileDir).getAbsolutePath() + "/";
    }

    @Bean
    public ProductFilePort productFilePort(final String root) {
        return new ProductFileAdaptor(root);
    }

}
