package dev.jihogrammer.fileitem.port.in;

import dev.jihogrammer.files.File;
import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;

import java.util.Collection;

public interface ItemFileFindUsage {

    Item findItemById(ItemId id);

    File findMainFileByItemId(ItemId id);

    Collection<File> findSubFilesByItemId(ItemId id);

}
