package dev.jihogrammer.domain.members.model;

import dev.jihogrammer.domain.members.exception.MemberValidationException;

import static java.util.Objects.isNull;

public record SignUpCommand(
        String username,
        String password,
        Integer age
) {

    public SignUpCommand {
        if (isNull(username) || username.isBlank()) {
            throw new MemberValidationException("username is blank.");
        }
        if (isNull(password) || password.isBlank()) {
            throw new MemberValidationException("password is blank.");
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;

        private String password;

        private Integer age;

        private Builder() {}

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder password(final String password) {
            this.password = password;
            return this;
        }

        public Builder age(final Integer age) {
            this.age = age;
            return this;
        }

        public SignUpCommand build() {
            return new SignUpCommand(this.name, this.password, this.age);
        }

    }

}
