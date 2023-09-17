package dev.jihogrammer.springboot.keyword;

public class KeywordService {
    private final KeywordRepository repository;

    public KeywordService(KeywordRepository keywordRepository) {
        repository = keywordRepository;
    }

    public int countOf(String keywordString) {
        return repository.findById(keywordString).map(Keyword::getCount).orElse(0);
    }
}
