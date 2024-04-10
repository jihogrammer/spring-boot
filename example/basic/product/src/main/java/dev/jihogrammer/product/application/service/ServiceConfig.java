package dev.jihogrammer.product.application.service;

import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.product.application.port.in.ProductDetailQuery;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import dev.jihogrammer.product.application.port.out.ProductDeliveryTypePort;
import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.application.port.out.ProductRegionPort;
import dev.jihogrammer.product.application.port.out.ProductTypePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public MemberId producerId() {
        return new MemberId(940614);
    }

    @Bean
    public ProductQuery productQuery(final ProductPort productPort) {
        return new ProductQueryService(productPort);
    }

    @Bean
    public ProductDetailQuery productDetailQuery(
        final ProductRegionPort productRegionPort,
        final ProductTypePort productTypePort,
        final ProductDeliveryTypePort productDeliveryTypePort
    ) {
        return new ProductDetailQueryService(productRegionPort, productTypePort, productDeliveryTypePort);
    }

    @Bean
    public ProductRegisterUseCase productRegisterUseCase(
        final ProductPort productPort,
        final MemberId producerId,
        final ProductRegionPort productRegionPort,
        final ProductTypePort productTypePort,
        final ProductDeliveryTypePort productDeliveryTypePort
    ) {
        return new ProductRegisterService(productPort, producerId, productRegionPort, productTypePort, productDeliveryTypePort);
    }

    @Bean
    public ProductUpdateUseCase productUpdateUseCase(
        final ProductPort productPort,
        final MemberId producerId,
        final ProductRegionPort productRegionPort,
        final ProductTypePort productTypePort,
        final ProductDeliveryTypePort productDeliveryTypePort
    ) {
        return new ProductUpdateService(productPort, producerId, productRegionPort, productTypePort, productDeliveryTypePort);
    }

}
