package dev.jihogrammer.fileitem.model;

import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.items.model.ItemId;

public record ItemFileStoreCommand(
    ItemId itemId,
    FileId fileId
) {
}
