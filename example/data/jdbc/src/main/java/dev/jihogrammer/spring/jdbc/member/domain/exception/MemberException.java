package dev.jihogrammer.spring.jdbc.member.domain.exception;

public class MemberException extends RuntimeException {

    public MemberException(final String message) {
        super(message);
    }

    public MemberException(final Throwable cause) {
        super(cause);
    }

    public MemberException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
