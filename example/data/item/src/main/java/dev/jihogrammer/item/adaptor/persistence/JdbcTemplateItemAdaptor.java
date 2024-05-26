package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.Data;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

class JdbcTemplateItemAdaptor implements Items {

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
            final var item = this.jdbcTemplate.queryForObject(
                    FIND_BY_ID_SQL,
                    this.itemRowMapper(),
                    id.value());
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.jdbcTemplate.query(
                SEARCH_SQL,
                this.itemRowMapper(),
                command.input(),
                command.minPrice(),
                command.maxPrice());
    }

    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> new Item(
                new ItemId(rs.getLong("ITEM_ID")),
                rs.getString("NAME"),
                rs.getInt("PRICE"),
                rs.getInt("QUANTITY"));
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
