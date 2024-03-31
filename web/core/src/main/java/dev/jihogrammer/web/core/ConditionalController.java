package dev.jihogrammer.web.core;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ConditionalController {

    Logger log = LoggerFactory.getLogger(ConditionalController.class);

    @PostConstruct
    default void init() {
        log.trace("{} is initialized.", this.getClass().getName());
    }

}
