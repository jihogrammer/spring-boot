package dev.jihogrammer.files;

import dev.jihogrammer.files.model.FileId;

public record File(FileId id, String name, String storedName) {
}
