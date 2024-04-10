package dev.jihogrammer.fileitem.port.in;

import dev.jihogrammer.fileitem.ItemFile;
import dev.jihogrammer.fileitem.model.ItemFileType;
import dev.jihogrammer.fileitem.port.out.ItemFiles;
import dev.jihogrammer.files.File;
import dev.jihogrammer.files.port.out.Files;
import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.port.out.Items;

import java.util.Collection;

public class ItemFileFindInteractor implements ItemFileFindUsage {

    private final Items items;

    private final Files files;

    private final ItemFiles itemFiles;

    public ItemFileFindInteractor(final Items items, final Files files, final ItemFiles itemFiles) {
        this.items = items;
        this.files = files;
        this.itemFiles = itemFiles;
    }

    @Override
    public Item findItemById(ItemId id) {
        return this.items.findById(id).orElseThrow();
    }

    @Override
    public File findMainFileByItemId(ItemId id) {
        return this.itemFiles.findByItemIdAndType(id, ItemFileType.MAIN).stream()
                .findFirst()
                .map(ItemFile::fileId)
                .map(this.files::findById)
                .orElseThrow();
    }

    @Override
    public Collection<File> findSubFilesByItemId(ItemId id) {
        return this.itemFiles.findByItemIdAndType(id, ItemFileType.SUB).stream()
            .map(ItemFile::fileId)
            .map(this.files::findById)
            .toList();
    }

}
