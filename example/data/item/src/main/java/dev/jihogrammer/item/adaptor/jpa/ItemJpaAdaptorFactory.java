package dev.jihogrammer.item.adaptor.jpa;

import dev.jihogrammer.item.application.port.out.Items;
import jakarta.persistence.EntityManager;

public class ItemJpaAdaptorFactory {

    public Items items(final EntityManager entityManager) {
        return new ItemJpaAdaptor(entityManager);
    }

}
