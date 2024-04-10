package dev.jihogrammer.filestorage.application.items.model;

import dev.jihogrammer.files.File;
import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;

import java.util.Collection;

public record ItemViewModel(
    ItemId id,
    String name,
    File mainFile,
    Collection<File> subFiles
) {

    public static ItemViewModel of(
        final Item item,
        final File mainFile,
        final Collection<File> subFiles
    ) {
        return new ItemViewModel(item.id(), item.name(), mainFile, subFiles);
    }

}
