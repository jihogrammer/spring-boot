package dev.jihogrammer.files.port.out;

import dev.jihogrammer.files.File;
import dev.jihogrammer.files.model.FileId;
import dev.jihogrammer.files.model.FileSaveCommand;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryFileRepository implements Files {

    private final Map<FileId, File> files;

    private final String rootDir;

    public InMemoryFileRepository(final String rootDir) {
        files = new ConcurrentHashMap<>();
        this.rootDir = rootDir;
    }

    @Override
    public String root() {
        return this.rootDir;
    }

    @Override
    public File save(final FileSaveCommand command) {
        var file = new File(
            IdGenerator.nextId(),
            command.filename(),
            NameGenerator.nextName(command.filename()));

        this.files.put(file.id(), file);

        return this.findById(file.id());
    }

    @Override
    public File findById(final FileId id) {
        return this.files.get(id);
    }

    private static class IdGenerator {

        static final AtomicLong SEQUENCE = new AtomicLong();

        static FileId nextId() {
            return new FileId(SEQUENCE.incrementAndGet());
        }

    }

    private static class NameGenerator {

        static final String BLANK = "";

        static final String DOT = ".";

        static String nextName(final String filename) {
            return UUID.randomUUID() + extractFileExtension(filename);
        }

        static String extractFileExtension(final String filename) {
            return filename.isBlank() ? BLANK : filename.substring(filename.lastIndexOf(DOT));
        }

    }

}
