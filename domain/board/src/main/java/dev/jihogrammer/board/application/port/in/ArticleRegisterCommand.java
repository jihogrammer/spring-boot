package dev.jihogrammer.board.application.port.in;

import lombok.Builder;

@Builder
public record ArticleRegisterCommand(
    String title,
    String contents
) {
}
