package dev.jihogrammer.board.adaptor.out.persistence;

import dev.jihogrammer.board.application.port.out.ArticlePort;

public class TestArticleAdaptorFactory {

    public static ArticlePort createArticlePort() {
        return new InMemoryArticleAdaptor();
    }

}
