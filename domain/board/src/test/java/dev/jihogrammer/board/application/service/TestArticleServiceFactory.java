package dev.jihogrammer.board.application.service;

import dev.jihogrammer.board.adaptor.out.persistence.TestArticleAdaptorFactory;
import dev.jihogrammer.board.application.port.in.ArticleDeleteUseCase;
import dev.jihogrammer.board.application.port.in.ArticleQuery;
import dev.jihogrammer.board.application.port.in.ArticleRegisterUseCase;
import dev.jihogrammer.board.application.port.in.ArticleUpdateUseCase;
import dev.jihogrammer.board.application.port.out.ArticlePort;

public class TestArticleServiceFactory {

    public static ArticleRegisterUseCase createArticleRegisterUseCase() {
        return createArticleRegisterUseCase(TestArticleAdaptorFactory.createArticlePort());
    }

    public static ArticleRegisterUseCase createArticleRegisterUseCase(final ArticlePort articlePort) {
        return new ArticleRegisterService(articlePort);
    }

    public static ArticleQuery createArticleQuery() {
        return createArticleQuery(TestArticleAdaptorFactory.createArticlePort());
    }

    public static ArticleQuery createArticleQuery(final ArticlePort articlePort) {
        return new ArticleService(articlePort);
    }

    public static ArticleUpdateUseCase createArticleUpdateUseCase() {
        return createArticleUpdateUseCase(TestArticleAdaptorFactory.createArticlePort());
    }

    public static ArticleUpdateUseCase createArticleUpdateUseCase(final ArticlePort articlePort) {
        final var articleQuery = createArticleQuery(articlePort);
        return new ArticleUpdateService(articlePort, articleQuery);
    }

    public static ArticleDeleteUseCase createArticleDeleteUseCase() {
        return createArticleDeleteUseCase(TestArticleAdaptorFactory.createArticlePort());
    }

    public static ArticleDeleteUseCase createArticleDeleteUseCase(final ArticlePort articlePort) {
        return new ArticleDeleteService(articlePort);
    }

}
