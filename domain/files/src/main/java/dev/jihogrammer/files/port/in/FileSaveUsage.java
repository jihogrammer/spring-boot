package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileSaveCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public interface FileSaveUsage {

    File save(FileSaveCommand command);

    default Collection<File> save(Collection<FileSaveCommand> commands) {
        var files = new ArrayList<File>();

        for (var command : commands) {
            if (command.isNotEmpty()) {
                files.add(this.save(command));
            }
        }

        return Collections.unmodifiableList(files);
    }

}
