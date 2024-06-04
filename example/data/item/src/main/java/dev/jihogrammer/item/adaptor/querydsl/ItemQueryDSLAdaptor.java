package dev.jihogrammer.item.adaptor.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.Optional;

import static dev.jihogrammer.item.adaptor.querydsl.QItemQueryDSLEntity.itemQueryDSLEntity;

class ItemQueryDSLAdaptor implements Items {

    private final EntityManager em;

    private final JPAQueryFactory queryFactory;

    ItemQueryDSLAdaptor(final EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Item save(final ItemSaveCommand command) {
        final ItemQueryDSLEntity entity;

        if (command.id() == null) {
            entity = ItemQueryDSLEntity.of(command);
            this.em.persist(entity);
        } else {
            entity = this.em.find(ItemQueryDSLEntity.class, command.id());
            entity.setName(command.name());
            entity.setPrice(command.price());
            entity.setQuantity(command.quantity());
        }

        return entity.toDomainEntity();
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        final var entity = this.em.find(ItemQueryDSLEntity.class, id.value());
        return Optional.ofNullable(entity)
                .map(ItemQueryDSLEntity::toDomainEntity);
    }

    @Deprecated
    public Collection<Item> oldSearch(final ItemSearchCommand command) {
        final var booleanBuilder = new BooleanBuilder();

        final var input = command.input();
        if (!input.isBlank()) {
            booleanBuilder.and(itemQueryDSLEntity.name.like('%' + input + '%'));
        }
        final var minPrice = command.minPrice();
        if (minPrice > 0) {
            booleanBuilder.and(itemQueryDSLEntity.price.goe(minPrice));
        }
        final var maxPrice = command.maxPrice();
        if (maxPrice < Integer.MAX_VALUE) {
            booleanBuilder.and(itemQueryDSLEntity.price.loe(maxPrice));
        }

        return this.queryFactory
                .select(itemQueryDSLEntity)
                .from(itemQueryDSLEntity)
                .where(booleanBuilder)
                .fetch()
                .stream()
                .map(ItemQueryDSLEntity::toDomainEntity)
                .toList();
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.queryFactory
                .select(itemQueryDSLEntity)
                .from(itemQueryDSLEntity)
                .where(nameLike(command.input()))
                .where(priceGreaterThanOrEquals(command.minPrice()), priceLessThanOrEquals(command.maxPrice()))
                .fetch()
                .stream()
                .map(ItemQueryDSLEntity::toDomainEntity)
                .toList();
    }

    private BooleanExpression nameLike(final String input) {
        if (input.isBlank()) {
            return null;
        }
        return itemQueryDSLEntity.name.like('%' + input + '%');
    }

    private BooleanExpression priceGreaterThanOrEquals(final Integer minPrice) {
        if (minPrice == 0) {
            return null;
        }
        return itemQueryDSLEntity.price.goe(minPrice);
    }

    private BooleanExpression priceLessThanOrEquals(final Integer maxPrice) {
        if (maxPrice == Integer.MAX_VALUE) {
            return null;
        }
        return itemQueryDSLEntity.price.loe(maxPrice);
    }

}
