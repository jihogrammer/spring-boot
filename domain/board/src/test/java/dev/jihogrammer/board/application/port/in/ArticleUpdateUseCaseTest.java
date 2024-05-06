package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.adaptor.out.persistence.TestArticleAdaptorFactory;
import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.application.service.TestArticleServiceFactory;
import dev.jihogrammer.board.domain.exception.BoardException;
import dev.jihogrammer.board.domain.model.ArticleId;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class ArticleUpdateUseCaseTest {

    ArticleRegisterUseCase registerUseCase;

    ArticleUpdateUseCase updateUseCase;

    @BeforeEach
    void setUp() {
        ArticlePort articlePort = TestArticleAdaptorFactory.createArticlePort();
        this.registerUseCase = TestArticleServiceFactory.createArticleRegisterUseCase(articlePort);
        this.updateUseCase = TestArticleServiceFactory.createArticleUpdateUseCase(articlePort);
    }

    @Test
    void update() {
        // given
        final var registerCommand = new ArticleRegisterCommand("hello", "world");
        final var registeredArticle = this.registerUseCase.register(registerCommand);

        final var title = "dev";
        final var contents = "jihogrammer";
        final var updateCommand = ArticleUpdateCommand.builder()
                .id(registeredArticle.id())
                .title(title)
                .contents(contents)
                .build();

        // when
        final var article = this.updateUseCase.update(updateCommand);
        log.info("article=[{}]", article);

        // then
        assertThat(article.id()).isEqualTo(updateCommand.id());
        assertThat(article.title()).isEqualTo(title);
        assertThat(article.contents()).isEqualTo(contents);
        assertThat(article.createdAt()).isEqualTo(registeredArticle.createdAt());
        assertThat(article.updatedAt()).isNotNull();
        assertThat(article.deletedAt()).isNull();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void blankTitle(final String title) {
        // given
        final var registerCommand = new ArticleRegisterCommand("hello", "world");
        final var registeredArticle = this.registerUseCase.register(registerCommand);

        final var contents = "jihogrammer";
        final var updateCommand = ArticleUpdateCommand.builder()
                .id(registeredArticle.id())
                .title(title)
                .contents(contents)
                .build();

        // when
        final ThrowingCallable when = () -> this.updateUseCase.update(updateCommand);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void blankContents(final String contents) {
        // given
        final var registerCommand = new ArticleRegisterCommand("hello", "world");
        final var registeredArticle = this.registerUseCase.register(registerCommand);

        final var title = "dev";
        final var updateCommand = ArticleUpdateCommand.builder()
                .id(registeredArticle.id())
                .title(title)
                .contents(contents)
                .build();

        // when
        final ThrowingCallable when = () -> this.updateUseCase.update(updateCommand);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @ParameterizedTest
    @NullSource
    void nullId(final ArticleId id) {
        // given
        final var updateCommand = ArticleUpdateCommand.builder()
                .id(id)
                .title("hello")
                .contents("world")
                .build();

        // when
        final ThrowingCallable when = () -> this.updateUseCase.update(updateCommand);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @Test
    void unknownId() {
        // given
        final var updateCommand = ArticleUpdateCommand.builder()
                .id(new ArticleId(Long.MIN_VALUE))
                .title("hello")
                .contents("world")
                .build();

        // when
        final ThrowingCallable when = () -> this.updateUseCase.update(updateCommand);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

}
