package dev.jihogrammer.item.login.adaptor.member;

import dev.jihogrammer.common.utils.LongValueSequenceGenerator;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.model.vo.MemberId;
import dev.jihogrammer.member.port.out.Members;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemberInMemoryRepository implements Members {
    private final Map<MemberId, Member> store;
    private final LongValueSequenceGenerator generator;

    public MemberInMemoryRepository() {
        this.store = new ConcurrentHashMap<>();
        this.generator = new LongValueSequenceGenerator();
    }

    @Override
    public Member save(final MemberRegisterCommand command) {
        MemberId memberId = new MemberId(this.generator.next());
        Member member = Member.MemberFactory.makeMember(memberId, command);
        this.store.put(memberId, member);
        return this.store.get(memberId);
    }

    @Override
    public Collection<Member> findAll() {
        return this.store.values();
    }

    @Override
    public Optional<Member> findById(final MemberId memberId) {
        return Optional.ofNullable(this.store.get(memberId));
    }

    @Override
    public Optional<Member> findByUsername(final String username) {
        return findAll().stream().filter(member -> member.username().equals(username)).findAny();
    }
}
