package dev.jihogrammer.filestorage.adaptor.out.persistence;

import dev.jihogrammer.product.adaptor.out.persistence.DefaultProductPersistenceAdaptorFactory;
import dev.jihogrammer.product.application.port.out.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStoragePersistenceAdaptorConfig {

    @Bean
    public ProductPort productPort() {
        return DefaultProductPersistenceAdaptorFactory.createProductPort();
    }

}
