package dev.jihogrammer.board.adaptor.out.persistence;

import dev.jihogrammer.board.application.port.out.ArticlePort;
import dev.jihogrammer.board.application.port.out.ArticleSaveCommand;
import dev.jihogrammer.board.domain.Article;
import dev.jihogrammer.board.domain.model.ArticleId;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class InMemoryArticleAdaptor implements ArticlePort {

    private final Map<ArticleId, Article> articleMap = new ConcurrentHashMap<>();

    @Override
    public Article save(final ArticleSaveCommand command) {
        final var builder = Article.builder()
                .title(command.title())
                .contents(command.contents());

        if (command.id() == null) {
            builder
                    .id(IdGenerator.next())
                    .createdAt(LocalDateTime.now());
        } else if (command.deletedAt() == null) {
            builder
                    .id(command.id())
                    .createdAt(command.createdAt())
                    .updatedAt(LocalDateTime.now());
        } else {
            builder
                    .id(command.id())
                    .createdAt(command.createdAt())
                    .updatedAt(command.updatedAt())
                    .deletedAt(command.deletedAt());
        }

        final var article = builder.build();
        this.articleMap.put(article.id(), article);

        return article;
    }

    @Override
    public Collection<Article> findAll(final int from, final int size) {
        return this.articleMap.values().stream()
                .filter(Article::isAccessible)
                .skip(from)
                .limit(size)
                .toList();
    }

    @Override
    public Optional<Article> findById(final ArticleId id) {
        return Optional.ofNullable(this.articleMap.get(id));
    }

    @Override
    public Collection<Article> findAllByTitle(final String title) {
        return this.articleMap.values().stream()
                .filter(Article::isAccessible)
                .filter(article -> article.title().contains(title))
                .toList();
    }

    @Override
    public Collection<Article> findAllByDuration(final LocalDateTime start, final LocalDateTime end) {
        final var startTime = start == null ? LocalDateTime.MIN : start;
        final var endTime = end == null ? LocalDateTime.MAX : end;

        return this.articleMap.values().stream()
                .filter(Article::isAccessible)
                .filter(article -> article.createdAt().isAfter(startTime))
                .filter(article -> article.createdAt().isBefore(endTime))
                .toList();
    }

    @Override
    public boolean deleteById(final ArticleId id) {
        final var article = this.findById(id).orElse(null);

        if (article == null || article.isDeleted()) {
            return false;
        }

        final var deletedArticle = Article.builder()
                .id(article.id())
                .title(article.title())
                .contents(article.contents())
                .createdAt(article.createdAt())
                .updatedAt(article.updatedAt())
                .deletedAt(LocalDateTime.now())
                .build();

        this.articleMap.put(deletedArticle.id(), deletedArticle);
        return true;
    }

    private static class IdGenerator {

        private static final AtomicLong sequence = new AtomicLong();

        static ArticleId next() {
            return new ArticleId(sequence.incrementAndGet());
        }

    }

}
