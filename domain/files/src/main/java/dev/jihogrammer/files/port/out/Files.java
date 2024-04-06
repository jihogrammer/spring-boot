package dev.jihogrammer.files.port.out;

import dev.jihogrammer.files.File;

public interface Files<T> {

    String root();

    File<T> save(File<T> file);

}
