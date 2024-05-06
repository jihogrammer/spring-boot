package dev.jihogrammer.board.application.service;

import dev.jihogrammer.board.application.port.in.ArticleQuery;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.domain.Article;
import dev.jihogrammer.board.domain.exception.BoardException;
import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;

@RequiredArgsConstructor
class ArticleService implements ArticleQuery {

    private final ArticlePort articlePort;

    @Override
    public Article findById(final ArticleId id) {
        if (id == null) {
            throw new BoardException("Article id is null.");
        }
        return this.articlePort.findById(id)
                .orElseThrow(() -> new BoardException("Could not find a article by '" + id + "'"));
    }

    @Override
    public Collection<Article> findAll(final Integer from, final Integer size) {
        if (from == null) {
            throw new BoardException("ArticleQuery from is null.");
        }
        if (size == null) {
            throw new BoardException("ArticleQuery size is null.");
        }
        if (from < FROM_MIN_VALUE) {
            throw new BoardException("ArticleQuery from is not valid: " + from);
        }
        if (size < SIZE_MIN_VALUE) {
            throw new BoardException("ArticleQuery size is not valid: " + size);
        }
        return this.articlePort.findAll(from, size);
    }

    @Override
    public Collection<Article> findAllByTitle(final String title) {
        return this.articlePort.findAllByTitle(title);
    }

    @Override
    public Collection<Article> findAllByDuration(final LocalDateTime start, final LocalDateTime end) {
        return this.articlePort.findAllByDuration(start, end);
    }

}
