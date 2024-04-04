package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileSaveCommand;
import dev.jihogrammer.files.exception.FileException;
import dev.jihogrammer.files.port.out.Files;

import java.util.UUID;

public class FileSaveInteractor implements FileSaveUsage {

    private static final String BLANK = "";

    private static final String DOT = ".";

    private final Files files;

    public FileSaveInteractor(final Files files) {
        this.files = files;
    }

    @Override
    public File save(final FileSaveCommand command) {
        if (command.isEmpty()) {
            throw new FileException("Command is empty.");
        }

        var filename = command.filename();
        var storedFilename = createStoredFilename(filename);
        var file = new File(filename, storedFilename);

        return this.files.save(file);
    }

    private String createStoredFilename(final String filename) {
        return UUID.randomUUID() + extractFileExtension(filename);
    }

    private String extractFileExtension(final String filename) {
        return filename.isBlank() ? BLANK : filename.substring(filename.lastIndexOf(DOT));
    }

}
