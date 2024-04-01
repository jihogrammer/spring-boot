package dev.jihogrammer.web.core;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ConditionalController {

    private Logger log() {
        return LoggerFactory.getLogger(ConditionalController.class);
    }

    @PostConstruct
    default void init() {
        var logger = this.log();
        logger.trace("{} is initialized.", this.getClass().getName());
    }

}
