package dev.jihogrammer.common.model;

import dev.jihogrammer.common.utils.StringUtils;

public interface StringSingleValueObject extends SingleValueObject<String> {
    @Override
    default boolean isEmpty() {
        return StringUtils.isEmpty(value());
    }
}
