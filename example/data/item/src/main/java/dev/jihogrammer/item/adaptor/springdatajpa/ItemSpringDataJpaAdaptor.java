package dev.jihogrammer.item.adaptor.springdatajpa;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

// 여기서는 굳이 @Repository 붙일 필요는 없다. 이미 ItemSpringDataJpaRepository 구현체는 스프링이 만들어 주는 거라 예외 변환이 된다.
//@Repository
@Transactional
@RequiredArgsConstructor
class ItemSpringDataJpaAdaptor implements Items {

    private final ItemSpringDataJpaRepository repository;

    @Override
    public Item save(final ItemSaveCommand command) {
        final var entity = ItemSpringDataJpaEntity.of(command);
        final var savedItem = this.repository.save(entity);
        return savedItem.toEntity();
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        return this.repository.findById(id.value())
                .map(ItemSpringDataJpaEntity::toEntity);
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.repository.findByNameLikeAndPriceBetween(
                "%" + command.input() + "%", command.minPrice(), command.maxPrice()).stream()
                .map(ItemSpringDataJpaEntity::toEntity)
                .toList();
    }

}
