package dev.jihogrammer.domain.members.intrastructure.adaptor.out;

import dev.jihogrammer.domain.members.model.MemberId;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.Members;

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
    public Member save(final SignUpCommand command) {
        Member member = new Member(
            MemberIdGenerator.nextId(),
            command.username(),
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
    public Optional<Member> findByUsername(String username) {
        return this.store.values().stream()
            .filter(member -> username.equals(member.username()))
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
