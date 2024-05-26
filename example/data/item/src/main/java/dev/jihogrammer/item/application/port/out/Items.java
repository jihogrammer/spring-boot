package dev.jihogrammer.item.application.port.out;

import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    String INSERT_SQL = "INSERT INTO ITEMS (NAME, PRICE, QUANTITY) VALUES (?, ?, ?)";

    String UPDATE_SQL = "UPDATE ITEMS SET NAME = ?, PRICE = ?, QUANTITY = ? WHERE ITEM_ID = ?";

    String FIND_BY_ID_SQL = "SELECT ITEM_ID, NAME, PRICE, QUANTITY FROM ITEMS WHERE ITEM_ID = ?";

    String SEARCH_SQL = "SELECT ITEM_ID, NAME, PRICE, QUANTITY FROM ITEMS WHERE NAME LIKE CONCAT('%', ?, '%') AND PRICE BETWEEN ? AND ?";

    Item save(ItemSaveCommand command);

    Optional<Item> findById(ItemId id);

    Collection<Item> search(ItemSearchCommand command);

}
