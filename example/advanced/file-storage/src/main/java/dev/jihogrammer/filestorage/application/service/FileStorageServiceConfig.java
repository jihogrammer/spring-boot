package dev.jihogrammer.filestorage.application.service;

import dev.jihogrammer.filestorage.application.port.in.FileStorageUseCase;
import dev.jihogrammer.filestorage.application.port.out.ProductFilePort;
import dev.jihogrammer.product.application.port.out.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageServiceConfig {

    @Bean
    public FileStorageUseCase fileStorageUseCase(
        final ProductPort productPort,
        final ProductFilePort productFilePort
    ) {
        return new FileStorageService(productPort, productFilePort);
    }

}
