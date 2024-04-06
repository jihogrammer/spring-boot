package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.port.out.Files;

public class FileReadInteractor<T> implements FileReadUsage {

    private final Files<T> files;

    public FileReadInteractor(final Files<T> files) {
        this.files = files;
    }

    @Override
    public String absolutePathOf(final String filename) {
        return this.files.root() + filename;
    }

}
