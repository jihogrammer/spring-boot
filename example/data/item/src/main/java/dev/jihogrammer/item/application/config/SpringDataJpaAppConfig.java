package dev.jihogrammer.item.application.config;

import dev.jihogrammer.item.adaptor.springdatajpa.ItemSpringDataJpaAdaptorFactory;
import dev.jihogrammer.item.adaptor.springdatajpa.ItemSpringDataJpaRepository;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.application.service.ItemServiceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDataJpaAppConfig {

    // 프로젝트 구조 상 어쩔 수 없이 JPA Entity, Repository 접근제한자를 public 설정
    // Configuration 파일이 동일 패키지에 있으면 접근제한자 이슈는 해결되나 여기서는 넘어가자
    @Bean
    public Items items(final ItemSpringDataJpaRepository repository) {
        return new ItemSpringDataJpaAdaptorFactory(repository).items();
    }

    @Bean
    public ItemQuery itemQuery(final Items items) {
        return new ItemServiceFactory().itemQuery(items);
    }

    @Bean
    public ItemUpdatePort itemUpdatePort(final Items items) {
        return new ItemServiceFactory().itemUpdatePort(items);
    }

}
