package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.adaptor.mybatis.ItemMapDto;
import dev.jihogrammer.item.adaptor.mybatis.ItemMapper;
import dev.jihogrammer.item.adaptor.mybatis.ItemSaveCommandDto;
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
        final var dto = ItemSaveCommandDto.of(command);

        if (command.id() == null) {
            this.itemMapper.save(dto);
        } else {
            this.itemMapper.update(dto);
        }

        return new Item(new ItemId(dto.getItemId()), dto.getName(), dto.getPrice(), dto.getQuantity());
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        return this.itemMapper.findById(id.value()).map(ItemMapDto::toEntity);
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.itemMapper.findAll(command).stream().map(ItemMapDto::toEntity).toList();
    }

}
