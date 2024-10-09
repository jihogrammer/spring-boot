package dev.jihogrammer.aop.exercise;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class StrongService {

    private final WeakRepository repository;

    @Trace
    String trim(final String param) {
        return this.repository.toUpperCase(param.trim());
    }

}
