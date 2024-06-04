package dev.jihogrammer.item.adaptor.querydsl;

import dev.jihogrammer.item.application.port.out.Items;
import jakarta.persistence.EntityManager;

public class ItemQueryDSLAdaptorFactory {

    public Items items(final EntityManager em) {
        return new ItemQueryDSLAdaptor(em);
    }

}
