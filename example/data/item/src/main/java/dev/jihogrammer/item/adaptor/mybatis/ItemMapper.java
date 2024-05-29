package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Optional;

@Mapper
@SuppressWarnings("all") // @see javac --help-lint
public interface ItemMapper {

    void save(MybatisItemSaveCommand command);

    void update(MybatisItemSaveCommand command);

    Collection<MybatisItem> findAll(ItemSearchCommand command);

    Optional<MybatisItem> findById(Long id);

}
