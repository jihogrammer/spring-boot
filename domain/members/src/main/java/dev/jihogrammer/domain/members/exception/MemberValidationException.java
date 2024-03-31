package dev.jihogrammer.domain.members.exception;

public class MemberValidationException extends IllegalArgumentException {

    public MemberValidationException(final String message) {
        super(message);
    }

}
