package dev.jihogrammer.item.adaptor.practical;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import jakarta.persistence.EntityManager;

import java.util.Collection;

import static dev.jihogrammer.item.adaptor.practical.QItemPracticalEntity.itemPracticalEntity;

class ItemPracticalQuery {

    private final JPAQueryFactory queryFactory;

    ItemPracticalQuery(final EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    Collection<ItemPracticalEntity> search(final ItemSearchCommand command) {
        return this.queryFactory
                .select(itemPracticalEntity)
                .from(itemPracticalEntity)
                .where(nameLike(command.input()))
                .where(priceGTE(command.minPrice()), priceLTE(command.maxPrice()))
                .fetch();
    }

    private BooleanExpression nameLike(final String input) {
        if (input.isBlank()) {
            return null;
        }
        return itemPracticalEntity.name.like('%' + input + '%');
    }

    private BooleanExpression priceGTE(final Integer minPrice) {
        if (minPrice == 0) {
            return null;
        }
        return itemPracticalEntity.price.goe(minPrice);
    }

    private BooleanExpression priceLTE(final Integer maxPrice) {
        if (maxPrice == Integer.MAX_VALUE) {
            return null;
        }
        return itemPracticalEntity.price.loe(maxPrice);
    }

}
