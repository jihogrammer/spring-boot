package dev.jihogrammer.item.adaptor.jpa;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository // 컴포넌트 스캔의 대상으로도 사용되지만, 예외 발생 시 JPA 예외를 Spring 예외로 변환하는 기능도 포함된다.
@Transactional
@RequiredArgsConstructor
class ItemJpaAdaptor implements Items {

    private static final String SEARCH_JPQL = """
                SELECT i
                  FROM ItemJpaEntity i
                 WHERE name LIKE CONCAT('%', :input, '%')
                   AND price BETWEEN :minPrice AND :maxPrice
                """;

    private final EntityManager entityManager;

    @Override
    public Item save(final ItemSaveCommand command) {
        final ItemJpaEntity jpaEntity;

        if (command.id() == null) {
            jpaEntity = ItemJpaEntity.of(command);
            this.entityManager.persist(jpaEntity);
        } else {
            jpaEntity = this.entityManager.find(ItemJpaEntity.class, command.id());
            jpaEntity.setName(command.name());
            jpaEntity.setPrice(command.price());
            jpaEntity.setQuantity(command.quantity());
        }

        return jpaEntity.toEntity();
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        final var itemJpaEntity = this.entityManager.find(ItemJpaEntity.class, id.value());

        if (itemJpaEntity == null) {
            return Optional.empty();
        }

        return Optional.of(itemJpaEntity.toEntity());
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        final TypedQuery<ItemJpaEntity> query = this.entityManager.createQuery(SEARCH_JPQL, ItemJpaEntity.class);

        query.setParameter("input", command.input());
        query.setParameter("minPrice", command.minPrice());
        query.setParameter("maxPrice", command.maxPrice());

        return query.getResultStream().map(ItemJpaEntity::toEntity).toList();
    }

}
