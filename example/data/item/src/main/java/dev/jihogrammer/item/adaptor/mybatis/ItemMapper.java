package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(ItemSaveCommandDto command);

    void update(ItemSaveCommandDto command);

    Collection<ItemMapDto> findAll(ItemSearchCommand command);

    Optional<ItemMapDto> findById(Long id);

}
