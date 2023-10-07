package dev.jihogrammer.springboot.keyword;

import dev.jihogrammer.springboot.config.RedisConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnableRedisRepositories
@ContextConfiguration(classes = {RedisConfig.class, KeywordConfig.class, KeywordRepository.class})
class KeywordServiceTest {
    @Autowired
    KeywordRepository keywordRepository;
    @Autowired
    KeywordService keywordService;

    static final String keyword = "123";

    @BeforeEach
    void setUp() {
        keywordRepository.save(new Keyword(keyword, 1));
    }

    @AfterEach
    void tearDown() {
        keywordRepository.findById(keyword).ifPresent(System.out::println);
        keywordRepository.deleteById(keyword);
    }

    @Test
    void testCountOf() {
        // given
        String keyword = KeywordServiceTest.keyword;
        // when
        int count = keywordService.countOf(keyword);
        // then
        assertThat(count).isEqualTo(1);
    }
}