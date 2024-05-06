package dev.jihogrammer.board.application.service;

import dev.jihogrammer.board.application.port.in.ArticleQuery;
import dev.jihogrammer.board.application.port.in.ArticleUpdateCommand;
import dev.jihogrammer.board.application.port.in.ArticleUpdateUseCase;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.application.port.out.ArticleSaveCommand;
import dev.jihogrammer.board.domain.Article;
import dev.jihogrammer.board.domain.exception.BoardException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ArticleUpdateService implements ArticleUpdateUseCase {

    private final ArticlePort articlePort;

    private final ArticleQuery articleQuery;

    @Override
    public Article update(final ArticleUpdateCommand command) {
        if (command.id() == null) {
            throw new BoardException("ArticleUpdateCommand id is null.");
        }

        final var registeredArticle = this.articleQuery.findById(command.id());

        final var saveCommand = ArticleSaveCommand.builder()
                .id(command.id())
                .title(command.title())
                .contents(command.contents())
                .createdAt(registeredArticle.createdAt())
                .updatedAt(registeredArticle.updatedAt())
                .deletedAt(registeredArticle.deletedAt())
                .build();

        return this.articlePort.save(saveCommand);
    }

}
