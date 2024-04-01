package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.model.Item;

import java.util.Collection;

public interface ItemReadUsage {

    Item findById(Long id) throws ItemException;

    Collection<Item> findAll();

}
