package dev.jihogrammer.files.model;

public abstract class FileSaveCommand {

    public abstract String filename();

    public abstract boolean isEmpty();

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

}
