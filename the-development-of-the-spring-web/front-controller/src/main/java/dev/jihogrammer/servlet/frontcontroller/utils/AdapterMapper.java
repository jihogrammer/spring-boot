package dev.jihogrammer.front_controller.utils;

import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class AdapterMapper {

    private final Set<Adapter> adapters;

    public Adapter map(final Controller controller) {
        for (var adapter : adapters) {
            if (adapter.supports(controller)) {
                log.debug("found adaptor: {}", adapter);
                return adapter;
            }
        }
        throw new IllegalArgumentException("failed to map adapter: " + controller.getClass().getSimpleName());
    }

}
