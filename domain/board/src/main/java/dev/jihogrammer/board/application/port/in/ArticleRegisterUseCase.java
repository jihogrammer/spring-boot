package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.domain.Article;

public interface ArticleRegisterUseCase {

    Article register(ArticleRegisterCommand command);

}
