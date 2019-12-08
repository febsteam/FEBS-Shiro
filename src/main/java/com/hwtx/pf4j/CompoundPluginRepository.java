package com.hwtx.pf4j;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;

/**
 * @author Decebal Suiu
 * @author Mário Franco
 */
public class CompoundPluginRepository implements PluginRepository {

    private List<PluginRepository> repositories = new ArrayList<>();

    public CompoundPluginRepository add(PluginRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("null not allowed");
        }

        repositories.add(repository);

        return this;
    }

    /**
     * Add a {@link PluginRepository} only if the {@code condition} is satisfied.
     *
     * @param repository
     * @param condition
     * @return
     */
    public CompoundPluginRepository add(PluginRepository repository, BooleanSupplier condition) {
        if (condition.getAsBoolean()) {
            return add(repository);
        }

        return this;
    }

    @Override
    public List<Path> getPluginPaths() {
        Set<Path> paths = new LinkedHashSet<>();
        for (PluginRepository repository : repositories) {
            paths.addAll(repository.getPluginPaths());
        }

        return new ArrayList<>(paths);
    }

    @Override
    public boolean deletePluginPath(Path pluginPath) {
        for (PluginRepository repository : repositories) {
            if (repository.deletePluginPath(pluginPath)) {
                return true;
            }
        }

        return false;
    }

}
