package dev.jihogrammer.item.adaptor.practical;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ItemPracticalAppConfig {

    private final ItemPracticalRepository repository;

    // 간단하게 설정하는 거 다 좋은데, 서비스의 접근제한자를 어쩔 수 없이 공개하는 게 마음에 들지 않는다.
    @Bean
    public ItemPracticalService itemPracticalService(final EntityManager entityManager) {
        return new ItemPracticalService(this.repository, new ItemPracticalQuery(entityManager));
    }

}
