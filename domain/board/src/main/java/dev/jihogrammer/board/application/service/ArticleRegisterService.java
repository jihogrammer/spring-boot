package dev.jihogrammer.board.application.service;

import dev.jihogrammer.board.application.port.in.ArticleRegisterCommand;
import dev.jihogrammer.board.application.port.in.ArticleRegisterUseCase;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.application.port.out.ArticleSaveCommand;
import dev.jihogrammer.board.domain.Article;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ArticleRegisterService implements ArticleRegisterUseCase {

    private final ArticlePort articlePort;

    @Override
    public Article register(final ArticleRegisterCommand command) {
        final var saveCommand = ArticleSaveCommand.builder()
                .title(command.title())
                .contents(command.contents())
                .build();

        return this.articlePort.save(saveCommand);
    }

}
