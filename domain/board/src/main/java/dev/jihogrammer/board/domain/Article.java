package dev.jihogrammer.board.domain;

import dev.jihogrammer.board.domain.exception.BoardException;
import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Article(
    ArticleId id,
    String title,
    String contents,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {

    public Article {
        if (id == null) {
            throw new BoardException("Article id is null.");
        }
        if (title == null || title.isBlank()) {
            throw new BoardException("Article title is blank.");
        }
        if (contents == null || contents.isBlank()) {
            throw new BoardException("Article contents is blank.");
        }
        if (createdAt == null) {
            throw new BoardException("Article createdAt is null.");
        }
    }

    public boolean isAccessible() {
        return this.deletedAt == null || this.deletedAt.isAfter(LocalDateTime.now());
    }

    public boolean isDeleted() {
        return !this.isAccessible();
    }

}
