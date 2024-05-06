package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.domain.Article;
import dev.jihogrammer.board.domain.model.ArticleId;

import java.time.LocalDateTime;
import java.util.Collection;

public interface ArticleQuery {

    int FROM_DEFAULT_VALUE = 0;

    int FROM_MIN_VALUE = 0;

    int SIZE_DEFAULT_VALUE = 10;

    int SIZE_MIN_VALUE = 1;

    Article findById(ArticleId id);

    default Collection<Article> findAll() {
        return this.findAll(FROM_DEFAULT_VALUE, SIZE_DEFAULT_VALUE);
    }

    Collection<Article> findAll(Integer from, Integer size);

    Collection<Article> findAllByTitle(String title);

    Collection<Article> findAllByDuration(LocalDateTime start, LocalDateTime end);

}
