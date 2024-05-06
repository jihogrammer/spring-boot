package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.domain.Article;

public interface ArticleUpdateUseCase {

    Article update(ArticleUpdateCommand command);

}
