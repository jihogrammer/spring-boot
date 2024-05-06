package dev.jihogrammer.board.application.port.in;

import dev.jihogrammer.board.application.service.TestArticleServiceFactory;
import dev.jihogrammer.board.domain.exception.BoardException;
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
class ArticleRegisterUseCaseTest {

    ArticleRegisterUseCase useCase;

    @BeforeEach
    void setUp() {
        this.useCase = TestArticleServiceFactory.createArticleRegisterUseCase();
    }

    @Test
    void register() {
        // given
        final var title = "hello";
        final var contents = "world";
        final var command = new ArticleRegisterCommand(title, contents);

        // when
        final var article = this.useCase.register(command);
        log.info("article=[{}]", article);

        // then
        assertThat(article.id()).isNotNull();
        assertThat(article.title()).isEqualTo(title);
        assertThat(article.contents()).isEqualTo(contents);
        assertThat(article.createdAt()).isNotNull();
        assertThat(article.updatedAt()).isNull();
        assertThat(article.deletedAt()).isNull();
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void blankTitle(final String title) {
        // given
        final var command = new ArticleRegisterCommand(title, "world");

        // when
        final ThrowingCallable when = () -> this.useCase.register(command);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void blankContents(final String contents) {
        // given
        final var command = new ArticleRegisterCommand("hello", contents);

        // when
        final ThrowingCallable when = () -> this.useCase.register(command);

        // then
        assertThatThrownBy(when).isInstanceOf(BoardException.class);
    }

}
