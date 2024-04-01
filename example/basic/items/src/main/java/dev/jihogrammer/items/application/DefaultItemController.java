package dev.jihogrammer.items.application;

import dev.jihogrammer.items.model.DeliveryCode;
import dev.jihogrammer.items.model.ItemType;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class DefaultItemController {

    protected static final Map<String, String> REGIONS = Map.of("SEOUL", "서울", "BUSAN", "부산", "JEJU", "제주");

    protected static final Collection<ItemType> ITEM_TYPES = Arrays.stream(ItemType.values()).toList();

    protected static final Collection<DeliveryCode> DELIVERY_CODES = List.of(
            new DeliveryCode("FAST", "빠른 배송"),
            new DeliveryCode("NORMAL", "일반 배송"),
            new DeliveryCode("SLOW", "느린 배송"));

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        return REGIONS;
    }

    @ModelAttribute("itemTypes")
    public Collection<ItemType> itemTypes() {
        return ITEM_TYPES;
    }

    @ModelAttribute("deliveryCodes")
    public Collection<DeliveryCode> deliveryCodes() {
        return DELIVERY_CODES;
    }

}
