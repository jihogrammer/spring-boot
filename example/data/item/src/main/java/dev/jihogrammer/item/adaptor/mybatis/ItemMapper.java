package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Optional;

@Mapper
@SuppressWarnings("all") // @see javac --help-lint
public interface ItemMapper {

    void save(ItemSaveCommandMybatisEntity command);

    void update(ItemSaveCommandMybatisEntity command);

    Collection<ItemMybatisEntity> findAll(ItemSearchCommand command);

    Optional<ItemMybatisEntity> findById(Long id);

}
