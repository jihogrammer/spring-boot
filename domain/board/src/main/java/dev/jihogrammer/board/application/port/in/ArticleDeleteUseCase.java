package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.domain.model.ArticleId;

public interface ArticleDeleteUseCase {

    boolean deleteById(ArticleId id);

}
