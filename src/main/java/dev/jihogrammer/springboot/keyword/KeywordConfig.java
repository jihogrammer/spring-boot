package dev.jihogrammer.springboot.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KeywordConfig {
    private final KeywordRepository keywordRepository;

    @Bean
    public KeywordService keywordService() {
        return new KeywordService(keywordRepository);
    }
}
