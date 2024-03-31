package dev.jihogrammer.members.infrastructure.adaptor.out;

import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.MemberId;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.Members;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
public class InMemoryMemberRepository implements Members {

    private final Map<MemberId, Member> members;

    @Override
    public Member save(final SignUpCommand command) {
        var member = new Member(
                MemberIdGenerator.nextId(),
                command.username(),
                command.password(),
                command.age());

        this.members.put(member.id(), member);

        return this.findById(member.id());
    }

    @Override
    public Member findById(final MemberId id) {
        return this.members.get(id);
    }

    @Override
    public Collection<Member> findAll() {
        return this.members.values();
    }

    @Override
    public Optional<Member> findByUsername(final String username) {
        return this.members.values().stream()
                .filter(member -> username.equals(member.username()))
                .findFirst();
    }

    private static class MemberIdGenerator {

        static final AtomicLong SEQUENCE = new AtomicLong();

        static MemberId nextId() {
            return new MemberId(SEQUENCE.incrementAndGet());
        }

    }

}
