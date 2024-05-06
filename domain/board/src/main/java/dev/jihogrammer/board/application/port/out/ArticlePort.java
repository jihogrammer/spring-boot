package dev.jihogrammer.board.application.port.out;

import dev.jihogrammer.board.domain.Article;
import dev.jihogrammer.board.domain.model.ArticleId;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface ArticlePort {

    Article save(ArticleSaveCommand command);

    Collection<Article> findAll(int from, int size);

    Optional<Article> findById(ArticleId id);

    Collection<Article> findAllByTitle(String title);

    Collection<Article> findAllByDuration(LocalDateTime start, LocalDateTime end);

    boolean deleteById(ArticleId id);

}
