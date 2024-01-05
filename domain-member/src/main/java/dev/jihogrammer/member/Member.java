package dev.jihogrammer.member;

import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.model.vo.MemberId;

import static dev.jihogrammer.common.utils.StringUtils.hasText;
import static java.util.Objects.isNull;

public class Member {
    private final MemberId memberId;
    private final String username;
    private final String password;

    /**
     * @see MemberFactory
     */
    private Member(final MemberId memberId, final String username, final String password) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
    }

    public MemberId memberId() {
        return this.memberId;
    }

    public String username() {
        return this.username;
    }

    public String password() {
        return this.password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;

        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }

    public static final class MemberFactory {
        public static Member makeMember(final MemberId memberId, final MemberRegisterCommand command) throws IllegalArgumentException {
            if (isNull(memberId)) {
                throw new IllegalArgumentException("member id is null");
            }
            if (hasText(command.getUsername())) {
                throw new IllegalArgumentException("member username is blank");
            }
            if (hasText(command.getPassword())) {
                throw new IllegalArgumentException("member password is blank");
            }

            return new Member(memberId, command.getUsername(), command.getPassword());
        }
    }
}
