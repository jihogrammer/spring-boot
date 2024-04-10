package dev.jihogrammer.fileitem.model;

import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.product.domain.model.ItemId;

public record ItemFileStoreCommand(
    ItemId itemId,
    FileId fileId
) {
}
