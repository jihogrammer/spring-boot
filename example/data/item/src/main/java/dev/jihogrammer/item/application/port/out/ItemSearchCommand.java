package dev.jihogrammer.item.application.port.out;

import dev.jihogrammer.item.domain.exception.ItemException;

public record ItemSearchCommand(
        String input,
        Integer minPrice,
        Integer maxPrice
) {

    public static final ItemSearchCommand DEFAULT_COMMAND = new ItemSearchCommand(null, 0, Integer.MAX_VALUE);

    public ItemSearchCommand {
        if (input != null) {
            input = input.trim().toLowerCase();
        }
        if (minPrice == null) {
            minPrice = DEFAULT_COMMAND.minPrice;
        }
        if (maxPrice == null) {
            maxPrice = DEFAULT_COMMAND.maxPrice;
        }
        if (minPrice > maxPrice) {
            throw new ItemException("ItemSearchCommand price condition is not valid. min=" + minPrice + "; max=" + maxPrice);
        }
    }

}
