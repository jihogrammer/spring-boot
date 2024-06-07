package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.Items;

public class MybatisAdaptorFactory {

    public Items mybatisItemAdaptor(final ItemMapper itemMapper) {
        return new MybatisItemAdaptor(itemMapper);
    }

}
