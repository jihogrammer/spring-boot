package dev.jihogrammer.member.model;

public record MemberSignUpCommand(String name, String password, Integer age) {

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

        public MemberSignUpCommand build() {
            return new MemberSignUpCommand(this.name, this.password, this.age);
        }

    }

}
