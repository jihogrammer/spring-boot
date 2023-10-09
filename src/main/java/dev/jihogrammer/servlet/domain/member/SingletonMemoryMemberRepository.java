package dev.jihogrammer.servlet.domain.member;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonMemoryMemberRepository implements MemberRepository {
    private final Map<Long, Member> store;
    private final AtomicLong sequence;

    private SingletonMemoryMemberRepository(final Map<Long, Member> store) {
        this.store = store;
        this.sequence = new AtomicLong();
    }

    public static SingletonMemoryMemberRepository getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SingletonMemoryMemberRepository INSTANCE = new SingletonMemoryMemberRepository(new ConcurrentHashMap<>());
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
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
