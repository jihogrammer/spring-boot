package dev.jihogrammer.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class MapUtils {
    public static <K, V> Map<K, V> immutableLinkedHashMap(final Object... input) {
        if ((input.length & 1) != 0) {
            throw new InternalError("length is odd");
        }

        Map<K, V> sourceLinkedHashMap = new LinkedHashMap<>();

        for (int i = 0; i < input.length; i += 2) {
            @SuppressWarnings("unchecked")
            K k = Objects.requireNonNull((K)input[i]);
            @SuppressWarnings("unchecked")
            V v = Objects.requireNonNull((V)input[i + 1]);
            sourceLinkedHashMap.put(k, v);
        }

        return new ImmutableLinkedHashMap<>(sourceLinkedHashMap);
    }

    private static class ImmutableLinkedHashMap<K, V> extends LinkedHashMap<K, V> {
        public ImmutableLinkedHashMap(final Map<K, V> source) {
            super(source);
        }

        @Override
        public V put(K key, V value) {
            throw new UnsupportedOperationException("this map is immutable map");
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            throw new UnsupportedOperationException("this map is immutable map");
        }

        @Override
        public V putIfAbsent(K key, V value) {
            throw new UnsupportedOperationException("this map is immutable map");
        }
    }
}
