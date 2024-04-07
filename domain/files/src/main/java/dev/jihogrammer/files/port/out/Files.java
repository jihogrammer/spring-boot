package dev.jihogrammer.files.port.out;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.files.model.FileSaveCommand;

public interface Files {

    String root();

    File findById(FileId id);

    File save(FileSaveCommand file);

}
