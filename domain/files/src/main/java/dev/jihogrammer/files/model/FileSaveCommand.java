package dev.jihogrammer.files.model;

public interface FileSaveCommand<T> {

    String filename();

    T delegate();

    boolean isEmpty();

    default boolean isNotEmpty() {
        return !this.isEmpty();
    }

}
