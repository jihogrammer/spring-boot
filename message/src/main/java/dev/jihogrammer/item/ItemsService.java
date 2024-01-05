package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.items.Items;
import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.vo.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final Items items;

    public Collection<Item> findAll() {
        return this.items.findAll();
    }

    public Item findById(final long itemId) {
        return this.items.findById(new ItemId(itemId));
    }

    public Item register(final ItemRegisterRequest request) {
        return this.items.save(request.mapToItemSaveCommand());
    }

    public Item update(final ItemUpdateRequest request) {
        return this.items.update(request.mapToItemUpdateCommand());
    }
}
