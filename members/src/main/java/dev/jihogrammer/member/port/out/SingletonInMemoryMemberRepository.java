package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.member.port.in.MemberRegisterCommand;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonInMemoryMemberRepository implements Members {

    private final Map<MemberId, Member> store;
    private final AtomicLong sequence;

    private SingletonInMemoryMemberRepository(final Map<MemberId, Member> store) {
        this.store = store;
        this.sequence = new AtomicLong();
    }

    public static SingletonInMemoryMemberRepository getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Member register(final MemberRegisterCommand command) {
        Member member = new Member(
            new MemberId(sequence.addAndGet(1)),
            command.getName(),
            command.getPassword(),
            command.getAge());
        store.put(member.id(), member);

        return store.get(member.id());
    }

    @Override
    public Member findById(final MemberId id) {
        return store.get(id);
    }

    @Override
    public Iterable<Member> findAll() {
        return store.values();
    }

    @Override
    public Optional<Member> findByUsername(String name) {
        return this.store.values().stream()
            .filter(member -> name.equals(member.name()))
            .findFirst();
    }

    private static class Holder {
        private static final SingletonInMemoryMemberRepository INSTANCE;

        static {
            INSTANCE = new SingletonInMemoryMemberRepository(new ConcurrentHashMap<>());
        }
    }

}
