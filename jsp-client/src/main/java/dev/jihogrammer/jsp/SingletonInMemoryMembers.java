package dev.jihogrammer.jsp;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.MemberRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonInMemoryMembers implements MemberRepository {
    private final Map<Long, Member> store;
    private final AtomicLong sequence;

    private SingletonInMemoryMembers(final Map<Long, Member> store) {
        this.store = store;
        this.sequence = new AtomicLong();
    }

    public static SingletonInMemoryMembers getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Member save(final Member member) {
        member.setId(sequence.addAndGet(1));
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(final Long id) {
        return store.get(id);
    }

    @Override
    public Iterable<Member> findAll() {
        return store.values();
    }

    private static class Holder {
        private static final SingletonInMemoryMembers INSTANCE = new SingletonInMemoryMembers(new ConcurrentHashMap<>());
    }
}
