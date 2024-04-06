package dev.jihogrammer.files;

import dev.jihogrammer.files.exception.FileException;

import java.io.Closeable;

public class File<T> implements Closeable {

    private final String name;

    private final String storedName;

    private final T delegate;

    private boolean isClosed;

    public File(final String name, final String storedName, final T delegate) {
        this.name = name;
        this.storedName = storedName;
        this.delegate = delegate;
    }

    public String name() {
        return this.name;
    }

    public String storedName() {
        return this.storedName;
    }

    public T delegate() {
        if (this.isClosed) {
            throw new FileException("This file was closed.");
        }
        return this.delegate;
    }

    @Override
    public void close() {
        this.isClosed = true;
    }

}
