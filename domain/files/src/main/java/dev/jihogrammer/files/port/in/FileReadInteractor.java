package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.exception.FileException;
import dev.jihogrammer.files.port.out.Files;

public class FileReadInteractor implements FileReadUsage {

    private final Files files;

    public FileReadInteractor(final Files files) {
        this.files = files;
    }

    @Override
    public File findByName(final String filename) {
        var originalNameFile = this.files.findByName(filename);
        if (originalNameFile.isPresent()) {
            return originalNameFile.get();
        }

        var storedNameFile = this.files.findByStoredName(filename);
        if (storedNameFile.isPresent()) {
            return storedNameFile.get();
        }

        throw new FileException("Could not find a file");
    }

    @Override
    public String absolutePathOf(final File file) {
        return this.files.root() + file.storedName();
    }

}
