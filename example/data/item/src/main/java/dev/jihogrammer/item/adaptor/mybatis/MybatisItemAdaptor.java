package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;

import java.util.Collection;
import java.util.Optional;

class MybatisItemAdaptor implements Items {

    private final ItemMapper itemMapper;

    MybatisItemAdaptor(final ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public Item save(final ItemSaveCommand command) {
        final var entity = ItemSaveCommandMybatisEntity.of(command);

        if (command.id() == null) {
            this.itemMapper.save(entity);
        } else {
            this.itemMapper.update(entity);
        }

        return entity.toDomain();
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        return this.itemMapper.findById(id.value()).map(ItemMybatisEntity::toDomain);
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.itemMapper.findAll(command).stream().map(ItemMybatisEntity::toDomain).toList();
    }

}
