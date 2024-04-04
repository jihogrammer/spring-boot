package dev.jihogrammer.files.port.out;

import dev.jihogrammer.files.File;

import java.util.Optional;

public interface Files {

    String root();

    Optional<File> findByName(String filename);

    Optional<File> findByStoredName(String storedFilename);

    File save(File file);

}
