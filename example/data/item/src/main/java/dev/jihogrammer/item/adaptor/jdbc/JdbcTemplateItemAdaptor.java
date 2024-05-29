package dev.jihogrammer.item.adaptor.jdbc;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.util.*;

class JdbcTemplateItemAdaptor implements Items {

    String INSERT_SQL = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY) VALUES (?, ?, ?)";

    String UPDATE_SQL = "UPDATE ITEMS SET NAME = ?, PRICE = ?, QUANTITY = ? WHERE ITEM_ID = ?";

    String FIND_BY_ID_SQL = "SELECT ITEM_ID, NAME, PRICE, QUANTITY FROM ITEMS WHERE ITEM_ID = ?";

    String SEARCH_SQL = "SELECT ITEM_ID, NAME, PRICE, QUANTITY FROM ITEMS WHERE NAME LIKE CONCAT('%', ?, '%') AND PRICE BETWEEN ? AND ?";

    private final JdbcTemplate jdbcTemplate;

    JdbcTemplateItemAdaptor(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(final ItemSaveCommand command) {
        if (command.id() == null) {
            final var keyHolder = new GeneratedKeyHolder();

            this.jdbcTemplate.update(connection -> {
                final var preparedStatement = connection.prepareStatement(INSERT_SQL, new String[]{"ITEM_ID"});

                preparedStatement.setString(1, command.name());
                preparedStatement.setInt(2, command.price());
                preparedStatement.setInt(3, command.quantity());

                return preparedStatement;
            }, keyHolder);

            if (keyHolder.getKey() == null) {
                throw new ItemException("KeyHolder is null.");
            }

            return new Item(
                    new ItemId(keyHolder.getKey().longValue()),
                    command.name(),
                    command.price(),
                    command.quantity());
        } else {
            this.jdbcTemplate.update(UPDATE_SQL, command.name(), command.price(), command.quantity(), command.id());

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
            final var itemJdbcEntity = this.jdbcTemplate.queryForObject(
                    FIND_BY_ID_SQL,
                    this.itemRowMapper(),
                    id.value());

            if (itemJdbcEntity == null) {
                throw new EmptyResultDataAccessException("ItemJdbcEntity is null.", 0);
            }

            return Optional.of(itemJdbcEntity.toEntity());
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        final var result = this.jdbcTemplate.query(
                SEARCH_SQL,
                this.itemRowMapper(),
                command.input(),
                command.minPrice(),
                command.maxPrice());
        return result.stream().map(ItemJdbcEntity::toEntity).toList();
    }

    private RowMapper<ItemJdbcEntity> itemRowMapper() {
        return BeanPropertyRowMapper.newInstance(ItemJdbcEntity.class);
    }

    @Data
    static class ItemJdbcEntity {

        private Long itemId;

        private String name;

        private Integer price;

        private Integer quantity;

        Item toEntity() {
            return new Item(
                    new ItemId(this.itemId),
                    this.name,
                    this.price,
                    this.quantity);
        }

    }

}
