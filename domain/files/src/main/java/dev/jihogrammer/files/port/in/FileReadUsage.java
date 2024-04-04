package dev.jihogrammer.files.port.in;

import dev.jihogrammer.files.File;

public interface FileReadUsage {

    File findByName(String filename);

    String absolutePathOf(File file);

    default String absolutePathOf(final String filename) {
        var file = this.findByName(filename);
        return this.absolutePathOf(file);
    }

}
