package dev.jihogrammer.fileitem;

import dev.jihogrammer.fileitem.model.ItemFileType;
import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.product.domain.model.ItemId;

public record ItemFile(
    ItemId itemId,
    FileId fileId,
    ItemFileType type
) {
}
