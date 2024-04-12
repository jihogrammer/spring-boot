package dev.jihogrammer.member.adaptor.out.persistence;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberId;
import dev.jihogrammer.member.application.port.out.MemberSaveCommand;
import dev.jihogrammer.member.application.port.out.MemberPort;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryMemberAdaptor implements MemberPort {

    private final Map<MemberId, Member> members = new ConcurrentHashMap<>();

    @Override
    public Member save(final MemberSaveCommand command) {
        var member = new Member(
            IdGenerator.next(),
            command.role(),
            command.name(),
            command.email(),
            command.password(),
            command.age());

        this.members.put(member.id(), member);

        return this.withoutPassword(member);
    }

    @Override
    public Optional<Member> findById(MemberId id) {
        return Optional.ofNullable(this.members.get(id))
                .map(this::withoutPassword);
    }

    @Override
    public Collection<Member> findAll() {
        return this.members.values().stream().map(this::withoutPassword).toList();
    }

    @Override
    public Optional<Member> findByEmailAndPassword(String email, String password) {
        return this.members.values().stream()
                .filter(member -> member.email().equals(email))
                .filter(member -> member.password().equals(password))
                .findFirst()
                .map(this::withoutPassword);
    }

    private Member withoutPassword(final Member member) {
        return new Member(
            member.id(),
            member.role(),
            member.name(),
            member.email(),
            null,
            member.age());
    }

    public void clear() {
        this.members.clear();
    }

    private static class IdGenerator {

        static final AtomicLong sequence = new AtomicLong();

        static MemberId next() {
            return new MemberId(sequence.incrementAndGet());
        }

    }

}
