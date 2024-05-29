package dev.jihogrammer.item.adaptor.jdbc;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

class NamedParameterJdbcTemplateItemAdaptor implements Items {

    private static final String UPDATE_SQL = """
            UPDATE %s SET NAME = :name, PRICE = :price, QUANTITY = :quantity
             WHERE ITEM_ID = :id;
            """.formatted(TABLE_NAME);

    private static final String FIND_BY_ID_SQL = """
            SELECT ITEM_ID, NAME, PRICE, QUANTITY
              FROM %s
             WHERE ITEM_ID = :id
            """.formatted(TABLE_NAME);

    private static final String SEARCH_SQL = """
            SELECT ITEM_ID, NAME, PRICE, QUANTITY
              FROM %s
             WHERE NAME LIKE CONCAT('%c', :input, '%c')
               AND PRICE BETWEEN :minPrice AND :maxPrice
            """.formatted(TABLE_NAME, '%', '%');

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final SimpleJdbcInsert simpleJdbcInsert;

    NamedParameterJdbcTemplateItemAdaptor(final DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns("ITEM_ID");
    }

    @Override
    public Item save(final ItemSaveCommand command) {
        if (command.id() == null) {
            final var params = new BeanPropertySqlParameterSource(command);
            final var key = this.simpleJdbcInsert.executeAndReturnKey(params);

            return new Item(
                    new ItemId(key.longValue()),
                    command.name(),
                    command.price(),
                    command.quantity());
        } else {
            final var params = new MapSqlParameterSource()
                    .addValue("id", command.id())
                    .addValue("name", command.name())
                    .addValue("price", command.price())
                    .addValue("quantity", command.quantity());
            this.jdbcTemplate.update(UPDATE_SQL, params);

            return new Item(
                    new ItemId(command.id()),
                    command.name(),
                    command.price(),
                    command.quantity());
        }
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        try {
            final var item = this.jdbcTemplate.queryForObject(
                    FIND_BY_ID_SQL,
                    Map.of("id", id.value()),
                    this.itemRowMapper());
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.jdbcTemplate.query(
                SEARCH_SQL,
                new BeanPropertySqlParameterSource(command),
                this.itemRowMapper());
    }

    private RowMapper<Item> itemRowMapper() {
        return  (rs, rowNum) -> new Item(
                new ItemId(rs.getLong("ITEM_ID")),
                rs.getString("NAME"),
                rs.getInt("PRICE"),
                rs.getInt("QUANTITY"));
    }

}
