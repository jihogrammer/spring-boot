package dev.jihogrammer.aop.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderRepository {

    private static final String EXCEPTION_ITEM_ID = "ex";

    private static final String VALIDATED_RESULT = "ok";

    public String save(final String itemId) {
        log.info("{}#save({})", this.getClass().getSimpleName(), itemId);

        if (EXCEPTION_ITEM_ID.equals(itemId)) {
            throw new IllegalStateException("An exception occurred.");
        }

        return VALIDATED_RESULT;
    }

}
