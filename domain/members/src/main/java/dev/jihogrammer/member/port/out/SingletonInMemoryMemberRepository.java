package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.model.Member;
import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.member.model.MemberSignUpCommand;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonInMemoryMemberRepository implements Members {

    private final Map<MemberId, Member> store;

    private SingletonInMemoryMemberRepository(final Map<MemberId, Member> store) {
        this.store = store;
    }

    public static Members getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Member save(final MemberSignUpCommand command) {
        Member member = new Member(
            MemberIdGenerator.nextId(),
            command.name(),
            command.password(),
            command.age());
        this.store.put(member.id(), member);

        return this.store.get(member.id());
    }

    @Override
    public Member findById(final MemberId id) {
        return this.store.get(id);
    }

    @Override
    public Collection<Member> findAll() {
        return this.store.values();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return this.store.values().stream()
            .filter(member -> name.equals(member.name()))
            .findFirst();
    }

    public void clear() {
        this.store.clear();
    }

    private static class Holder {

        private static final SingletonInMemoryMemberRepository INSTANCE;

        static {
            INSTANCE = new SingletonInMemoryMemberRepository(new ConcurrentHashMap<>());
        }

    }

    private static class MemberIdGenerator {

        private static final AtomicLong SEQUENCE;

        static {
            SEQUENCE = new AtomicLong();
        }

        static MemberId nextId() {
            return new MemberId(SEQUENCE.addAndGet(1));
        }

    }

}
