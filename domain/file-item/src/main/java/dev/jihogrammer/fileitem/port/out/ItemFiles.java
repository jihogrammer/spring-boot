package dev.jihogrammer.fileitem.port.out;

import dev.jihogrammer.fileitem.ItemFile;
import dev.jihogrammer.fileitem.model.ItemFileType;
import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemId;

import java.util.Collection;

public interface ItemFiles {

    ItemFile save(ItemFile itemFile);

    Collection<ItemFile> findByItemId(ItemId id);

    ItemFile findByFileId(FileId id);

    Collection<ItemFile> findByItemIdAndType(ItemId id, ItemFileType type);

}
