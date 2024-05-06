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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleQueryTest {

    ArticleQuery articleQuery;

    ArticleRegisterUseCase registerUseCase;

    ArticleDeleteUseCase deleteUseCase;

    @BeforeEach
    void setUp() {
        ArticlePort articlePort = TestArticleAdaptorFactory.createArticlePort();
        this.articleQuery = TestArticleServiceFactory.createArticleQuery(articlePort);
        this.registerUseCase = TestArticleServiceFactory.createArticleRegisterUseCase(articlePort);
        this.deleteUseCase = TestArticleServiceFactory.createArticleDeleteUseCase(articlePort);
    }

    @Test
    void findById() {
        // given
        final var registerCommand = ArticleRegisterCommand.builder().title("hello").contents("world").build();
        final var registeredArticle = this.registerUseCase.register(registerCommand);

        // when
        final var foundArticle = this.articleQuery.findById(registeredArticle.id());

        // then
        assertThat(foundArticle)
                .isNotNull()
                .isEqualTo(registeredArticle);
    }

    @ParameterizedTest
    @NullSource
    void findByNullId(final ArticleId id) {
        // when
        final ThrowingCallable when = () -> this.articleQuery.findById(id);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {Long.MIN_VALUE, 0, Long.MAX_VALUE})
    void findByUnknownId(final Long idValue) {
        // given
        final var id = new ArticleId(idValue);

        // when
        final ThrowingCallable when = () -> this.articleQuery.findById(id);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @Test
    void findAll() {
        // given
        IntStream.range(0, ArticleQuery.SIZE_DEFAULT_VALUE * 2)
                .forEach(i -> this.registerUseCase.register(ArticleRegisterCommand.builder()
                            .title("hello")
                            .contents("world")
                            .build()));

        // when
        final var foundArticles = this.articleQuery.findAll();

        // then
        assertThat(foundArticles).size().isEqualTo(ArticleQuery.SIZE_DEFAULT_VALUE);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 10", "10, 20", "20, 30"})
    void findAllFromAndSize(final int from, final int size) {
        // given
        IntStream.range(0, ArticleQuery.SIZE_DEFAULT_VALUE * 5)
            .forEach(i -> this.registerUseCase.register(ArticleRegisterCommand.builder()
                .title("hello")
                .contents("world")
                .build()));

        // when
        final var foundArticles = this.articleQuery.findAll(from, size);

        // then
        assertThat(foundArticles).size().isEqualTo(size);
    }

}
