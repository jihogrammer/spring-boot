package dev.jihogrammer.item;

import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.domain.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemsService {
    private final Items items;

    public List<ItemView> findAll() {
        return this.items.findAll().stream().map(ItemView::of).toList();
    }
}
