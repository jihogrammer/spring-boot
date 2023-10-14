package dev.jihogrammer.servlet;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class SingletonMemoryMembers implements Members {
    private final Map<Long, Member> store;
    private final AtomicLong sequence;

    private SingletonMemoryMembers(final Map<Long, Member> store) {
        this.store = store;
        this.sequence = new AtomicLong();
    }

    public static SingletonMemoryMembers getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SingletonMemoryMembers INSTANCE = new SingletonMemoryMembers(new ConcurrentHashMap<>());
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
