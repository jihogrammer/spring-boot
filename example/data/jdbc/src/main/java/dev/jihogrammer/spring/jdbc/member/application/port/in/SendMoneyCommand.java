package dev.jihogrammer.spring.jdbc.member.application.port.in;

import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;

public record SendMoneyCommand(
    MemberId senderId,
    MemberId receiverId,
    Integer money
) {

    public SendMoneyCommand {
        if (senderId == null) {
            throw new MemberException("senderId is null.");
        }
        if (receiverId == null) {
            throw new MemberException("receiverId is null.");
        }
        if (money == null) {
            throw new MemberException("money is null.");
        }
        if (money <= 0) {
            throw new MemberException("money is negative value.");
        }
    }

}
