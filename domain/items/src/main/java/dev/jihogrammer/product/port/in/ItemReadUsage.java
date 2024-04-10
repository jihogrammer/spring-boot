package dev.jihogrammer.product.port.in;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.exception.ItemException;

import java.util.Collection;

public interface ItemReadUsage {

    Item findById(Long id) throws ItemException;

    Collection<Item> findAll();

}
