package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.application.port.out.Items;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

public class ItemPersistenceAdaptorFactory {

    public Items inMemoryItemAdaptor() {
        return new InMemoryItemAdaptor(new ConcurrentHashMap<>());
    }

    public Items jdbcTemplateItemAdaptor(final DataSource dataSource) {
        return new JdbcTemplateItemAdaptor(dataSource);
    }

    public Items namedParameterJdbcTemplateItemAdaptor(final DataSource dataSource) {
        return new NamedParameterJdbcTemplateItemAdaptor(dataSource);
    }

}
