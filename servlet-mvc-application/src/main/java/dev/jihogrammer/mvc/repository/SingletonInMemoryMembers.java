package dev.jihogrammer.mvc.repository;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonInMemoryMembers implements Members {
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
        private static final SingletonInMemoryMembers INSTANCE;

        static {
            Map<Long, Member> store = new ConcurrentHashMap<>();
            INSTANCE = new SingletonInMemoryMembers(store);
        }
    }
}
