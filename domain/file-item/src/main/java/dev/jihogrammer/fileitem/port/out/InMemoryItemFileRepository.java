package dev.jihogrammer.fileitem.port.out;

import dev.jihogrammer.fileitem.ItemFile;
import dev.jihogrammer.fileitem.model.ItemFileType;
import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.product.domain.model.ItemId;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryItemFileRepository implements ItemFiles {

    private final Set<ItemFile> itemFiles = ConcurrentHashMap.newKeySet();

    @Override
    public ItemFile save(final ItemFile itemFile) {
        this.itemFiles.add(itemFile);
        return itemFile;
    }

    @Override
    public Collection<ItemFile> findByItemId(ItemId id) {
        return this.itemFiles.stream()
                .filter(itemFile -> itemFile.itemId().equals(id))
                .toList();
    }

    @Override
    public ItemFile findByFileId(FileId id) {
        return this.itemFiles.stream()
                .filter(itemFile -> itemFile.fileId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Collection<ItemFile> findByItemIdAndType(ItemId id, ItemFileType type) {
        return this.itemFiles.stream()
                .filter(itemFile -> itemFile.itemId().equals(id))
                .filter(itemFile -> itemFile.type().equals(type))
                .toList();
    }

}
