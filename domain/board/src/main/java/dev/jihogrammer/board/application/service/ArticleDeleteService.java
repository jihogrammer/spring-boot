package dev.jihogrammer.board.application.service;

import dev.jihogrammer.board.application.port.in.ArticleDeleteUseCase;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.domain.exception.BoardException;
import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ArticleDeleteService implements ArticleDeleteUseCase {

    private final ArticlePort articlePort;

    @Override
    public boolean deleteById(final ArticleId id) {
        if (id == null) {
            throw new BoardException("ArticleId is null.");
        }
        return this.articlePort.deleteById(id);
    }

}
