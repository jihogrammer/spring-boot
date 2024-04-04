package dev.jihogrammer.files.exception;

public class FileException extends RuntimeException {

    public FileException() {}

    public FileException(final String message) {
        super(message);
    }

    public FileException(final Throwable e) {
        super(e);
    }

    public FileException(final String message, final Throwable e) {
        super(message, e);
    }

}
