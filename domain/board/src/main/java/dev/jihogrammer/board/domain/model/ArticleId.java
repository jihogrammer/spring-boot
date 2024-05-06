package dev.jihogrammer.board.domain.model;

import dev.jihogrammer.board.domain.exception.BoardException;

public record ArticleId(Long value) {

    public ArticleId {
        if (value == null) {
            throw new BoardException("ArticleId value is null.");
        }
    }

}
