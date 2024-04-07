package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.port.out.Files;

public class FileReadInteractor implements FileReadUsage {

    private final Files files;

    public FileReadInteractor(final Files files) {
        this.files = files;
    }

    @Override
    public String absolutePathOf(final String filename) {
        return this.files.root() + filename;
    }

}
