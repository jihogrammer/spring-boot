package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.adaptor.out.persistence.TestArticleAdaptorFactory;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.application.service.TestArticleServiceFactory;
import dev.jihogrammer.board.domain.exception.BoardException;
import dev.jihogrammer.board.domain.model.ArticleId;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleDeleteUseCaseTest {

    ArticleRegisterUseCase registerUseCase;

    ArticleQuery articleQuery;

    ArticleDeleteUseCase deleteUseCase;

    @BeforeEach
    void setUp() {
        ArticlePort articlePort = TestArticleAdaptorFactory.createArticlePort();
        this.registerUseCase = TestArticleServiceFactory.createArticleRegisterUseCase(articlePort);
        this.articleQuery = TestArticleServiceFactory.createArticleQuery(articlePort);
        this.deleteUseCase = TestArticleServiceFactory.createArticleDeleteUseCase(articlePort);
    }

    @Test
    void delete() {
        // given
        final var registerCommand = new ArticleRegisterCommand("hello", "world");
        final var registeredArticle = this.registerUseCase.register(registerCommand);

        // when
        final var condition = this.deleteUseCase.deleteById(registeredArticle.id());
        final var foundArticle = this.articleQuery.findById(registeredArticle.id());

        // then
        assertThat(condition).isTrue();
        assertThat(foundArticle.isDeleted()).isTrue();
        assertThat(this.articleQuery.findAll()).doesNotContain(foundArticle);
    }

    @ParameterizedTest
    @ValueSource(longs = {Long.MIN_VALUE, 0, Long.MAX_VALUE})
    void unknownId(final Long idValue) {
        // when
        final var condition = this.deleteUseCase.deleteById(new ArticleId(idValue));

        // then
        assertThat(condition).isFalse();
    }

    @ParameterizedTest
    @NullSource
    void nullId(final ArticleId id) {
        // when
        final ThrowingCallable when = () -> this.deleteUseCase.deleteById(id);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

}
