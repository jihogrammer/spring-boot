package dev.jihogrammer.item.adaptor.practical;

import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
public class ItemPracticalService implements ItemUpdatePort, ItemQuery {

    private final ItemPracticalRepository repository;

    private final ItemPracticalQuery query;

    @Override
    public Item register(final ItemSaveCommand command) {
        return this.repository
                .save(ItemPracticalEntity.of(command))
                .toDomainEntity();
    }

    @Override
    public Item update(final ItemSaveCommand command) {
        final var entity = this.repository
                .findById(command.id())
                .orElseThrow(() -> new ItemException("Could not find item by + " + command.id()));

        entity.setName(command.name());
        entity.setPrice(command.price());
        entity.setQuantity(command.quantity());

        return entity.toDomainEntity();
    }

    @Override
    public Optional<Item> findById(ItemId id) {
        return this.repository
                .findById(id.value())
                .map(ItemPracticalEntity::toDomainEntity);
    }

    @Override
    public Collection<Item> search(ItemSearchCommand command) {
        return this.query
                .search(command)
                .stream()
                .map(ItemPracticalEntity::toDomainEntity).toList();
    }

}
