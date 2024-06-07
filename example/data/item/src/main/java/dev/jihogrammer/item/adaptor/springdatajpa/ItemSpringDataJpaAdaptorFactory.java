package dev.jihogrammer.item.adaptor.springdatajpa;

import dev.jihogrammer.item.application.port.out.Items;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemSpringDataJpaAdaptorFactory {

    private final ItemSpringDataJpaRepository repository;

    public Items items() {
        return new ItemSpringDataJpaAdaptor(this.repository);
    }

}
