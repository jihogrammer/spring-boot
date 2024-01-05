package dev.jihogrammer.member;

import dev.jihogrammer.member.model.vo.MemberId;

public record Member(MemberId memberId, String username, String password) {}
