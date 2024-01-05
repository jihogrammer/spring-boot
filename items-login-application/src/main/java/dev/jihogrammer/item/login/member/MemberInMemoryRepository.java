package dev.jihogrammer.item.login.member;

import dev.jihogrammer.common.utils.LongValueSequenceGenerator;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.model.vo.MemberId;
import dev.jihogrammer.member.port.out.Members;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberInMemoryRepository implements Members {
    private final Map<MemberId, Member> store;
    private final LongValueSequenceGenerator generator;

    public MemberInMemoryRepository() {
        this.store = new ConcurrentHashMap<>();
        this.generator = new LongValueSequenceGenerator();
    }

    @Override
    public Member register(final MemberRegisterCommand command) {
        MemberId memberId = new MemberId(this.generator.next());
        Member member = Member.MemberFactory.makeMember(memberId, command);
        this.store.put(memberId, member);
        return this.store.get(memberId);
    }
}
