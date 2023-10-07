package dev.jihogrammer.springboot.keyword;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "keyword")
@RequiredArgsConstructor
@Getter
@ToString
public class Keyword {
    @Id
    private final String keyword;
    private final int count;
}
