package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileSaveCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public interface FileSaveUsage<T> {

    File<T> save(FileSaveCommand<T> command);

    default Collection<File<T>> save(Collection<FileSaveCommand<T>> commands) {
        var files = new ArrayList<File<T>>();

        for (var command : commands) {
            if (command.isNotEmpty()) {
                files.add(this.save(command));
            }
        }

        return Collections.unmodifiableList(files);
    }

}
