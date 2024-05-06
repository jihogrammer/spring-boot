package dev.jihogrammer.board.application.port.out;

import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ArticleSaveCommand(
    ArticleId id,
    String title,
    String contents,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {
}
