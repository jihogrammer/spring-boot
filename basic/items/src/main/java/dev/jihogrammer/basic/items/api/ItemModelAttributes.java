package dev.jihogrammer.basic.items.api;

import dev.jihogrammer.items.model.DeliveryCode;
import dev.jihogrammer.items.model.ItemType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

interface ItemModelAttributes {

    Map<String, String> REGIONS = Map.of("SEOUL", "서울", "BUSAN", "부산", "JEJU", "제주");

    Collection<ItemType> ITEM_TYPES = Arrays.stream(ItemType.values()).toList();

    Collection<DeliveryCode> DELIVERY_CODES = List.of(
            new DeliveryCode("FAST", "빠른 배송"),
            new DeliveryCode("NORMAL", "일반 배송"),
            new DeliveryCode("SLOW", "느린 배송"));

}
