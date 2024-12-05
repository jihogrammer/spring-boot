package dev.jihogrammer.spring.boot.autoconfig.member.domain;

public record MemberId(String value) {

    public MemberId {
        if (value == null || value.isBlank()) {
            throw new MemberException("MemberId value is blank.");
        }
    }

}
