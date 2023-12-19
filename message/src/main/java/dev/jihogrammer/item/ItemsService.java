package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.domain.Items;
import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.vo.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
