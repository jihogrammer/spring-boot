package dev.jihogrammer.product.adaptor.out.persistence;

import dev.jihogrammer.product.application.port.out.ProductDeliveryTypePort;
import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.application.port.out.ProductRegionPort;
import dev.jihogrammer.product.application.port.out.ProductTypePort;
import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Bean
    public ProductPort products() {
        return new InMemoryProductAdaptor();
    }

    @Bean
    public ProductRegionPort regions() {
        var adaptor = new InMemoryProductRegionAdaptor();

        adaptor.save(new ProductRegion("SEOUL", "서울"));
        adaptor.save(new ProductRegion("BUSAN", "부산"));
        adaptor.save(new ProductRegion("JEJU", "제주"));

        return adaptor;
    }

    @Bean
    public ProductTypePort types() {
        var adaptor = new InMemoryProductTypeAdaptor();

        adaptor.save(new ProductType("BOOK", "도서"));
        adaptor.save(new ProductType("FOOD", "음식"));
        adaptor.save(new ProductType("ETC", "기타"));

        return adaptor;
    }

    @Bean
    public ProductDeliveryTypePort deliveryTypes() {
        var adaptor = new InMemoryProductProductDeliveryTypeAdaptor();

        adaptor.save(new ProductDeliveryType("FAST", "빠른 배송"));
        adaptor.save(new ProductDeliveryType("NORMAL", "보통 배송"));
        adaptor.save(new ProductDeliveryType("SLOW", "느린 배송"));

        return adaptor;
    }

}
