package dev.jihogrammer.filestorage.application.items;

import dev.jihogrammer.filestorage.application.items.model.ItemRegisterPayload;
import dev.jihogrammer.product.domain.model.ItemId;
import org.springframework.core.io.Resource;

public interface FileStoreUsage {

    void saveFiles(final ItemId id, final ItemRegisterPayload payload);

    Resource resource(final String storedFilename);

}
