package dev.jihogrammer.product.application.service;

import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import dev.jihogrammer.product.application.port.out.ProductPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageProductServiceConfig {

    @Bean
    public ProductQuery productQuery(final ProductPort productPort) {
        return new ProductQueryService(productPort);
    }

    @Bean
    public ProductRegisterUseCase productRegisterUseCase(final ProductPort productPort) {
        return new ProductRegisterService(productPort, null, null, null);
    }

    @Bean
    public ProductUpdateUseCase productUpdateUseCase(final ProductPort productPort) {
        return new ProductUpdateService(productPort, null, null, null);
    }

}
