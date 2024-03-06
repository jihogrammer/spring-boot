package dev.jihogrammer.item;

import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.port.out.ItemRegisterCommand;
import dev.jihogrammer.items.port.out.ItemUpdateCommand;
import dev.jihogrammer.items.port.out.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final Items items;

    public List<ItemView> findAll() {
        return this.items.findAll().stream().map(ItemView::of).toList();
    }

    public ItemView findById(final Long itemId) {
        return ItemView.of(this.items.findById(new ItemId(itemId)));
    }

    public ItemView register(final ItemRegisterCommand command) {
        return ItemView.of(this.items.save(command));
    }

    public ItemView update(final ItemUpdateCommand command) {
        return ItemView.of(this.items.update(command));
    }
}
