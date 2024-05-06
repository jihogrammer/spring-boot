package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.Builder;

@Builder
public record ArticleUpdateCommand(
    ArticleId id,
    String title,
    String contents
) {
}
