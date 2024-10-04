package dev.jihogrammer.aop.order;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private static final String EXCEPTION_ITEM_ID = "ex";

    private static final String VALIDATED_RESULT = "ok";

    public String save(final String itemId) {
        if (EXCEPTION_ITEM_ID.equals(itemId)) {
            throw new IllegalStateException("An exception occurred.");
        }

        return VALIDATED_RESULT;
    }

}
