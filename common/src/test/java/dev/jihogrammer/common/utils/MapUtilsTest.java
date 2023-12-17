package dev.jihogrammer.common.utils;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MapUtilsTest {
    @Test
    void isImmutableLinkedHashMapSequential() {
        // given
        Map<String, Integer> immutableLinkedHashMap = MapUtils.immutableLinkedHashMap("A", 1, "B", 2, "C", 3);
        Iterator<String> keyIterator = Arrays.asList("A", "B", "C").iterator();
        Iterator<Integer> valueIterator = Arrays.asList(1, 2, 3).iterator();
        // when
        for (String key : immutableLinkedHashMap.keySet()) {
            // then
            assertThat(key).isEqualTo(keyIterator.next());
            assertThat(immutableLinkedHashMap.get(key)).isEqualTo(valueIterator.next());
        }
    }

    @Test
    void putOfImmutableHashMap() {
        // given
        Map<String, Integer> immutableLinkedHashMap = MapUtils.immutableLinkedHashMap("A", 1, "B", 2, "C", 3);
        // when
        ThrowingCallable when = () -> immutableLinkedHashMap.put("D", 4);
        // then
        assertThatThrownBy(when).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void putAllOfImmutableHashMap() {
        // given
        Map<String, Integer> immutableLinkedHashMap = MapUtils.immutableLinkedHashMap("A", 1, "B", 2, "C", 3);
        // when
        ThrowingCallable when = () -> immutableLinkedHashMap.putAll(Map.of("D", 4, "E", 5));
        // then
        assertThatThrownBy(when).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void putIfAbsentOfImmutableHashMap() {
        // given
        Map<String, Integer> immutableLinkedHashMap = MapUtils.immutableLinkedHashMap("A", 1, "B", 2, "C", 3);
        // when
        ThrowingCallable when = () -> immutableLinkedHashMap.putIfAbsent("D", 4);
        // then
        assertThatThrownBy(when).isInstanceOf(UnsupportedOperationException.class);
    }
}
