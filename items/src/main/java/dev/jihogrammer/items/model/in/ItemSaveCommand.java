package dev.jihogrammer.items.model.in;

import dev.jihogrammer.items.model.vo.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemSaveCommand {
    public static final ItemSaveCommand EMPTY_COMMAND = new ItemSaveCommand(null, null, null);

    private final String name;
    private final Integer price;
    private final Integer quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;
}
