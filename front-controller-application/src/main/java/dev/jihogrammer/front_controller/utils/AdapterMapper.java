package dev.jihogrammer.front_controller.utils;

import dev.jihogrammer.front_controller.model.Adapter;
import dev.jihogrammer.front_controller.model.Controller;

import java.util.Collection;

public class AdapterMapper {
    private final Collection<Adapter> adapters;

    public AdapterMapper(final Collection<Adapter> adapters) {
        this.adapters = adapters;
    }

    public Adapter map(final Controller controller) {
        for (Adapter adapter : adapters) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("failed to map adapter: " + controller.getClass().getSimpleName());
    }
}
