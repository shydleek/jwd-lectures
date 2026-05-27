package org.epam.jwd.repository;

import org.epam.jwd.exception.CalculationRegistryNotFoundException;
import org.epam.jwd.model.CalculationsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListCalculationsRegistryRepository implements Repository<CalculationsRegistry> {

    private static ListCalculationsRegistryRepository instance;

    private final List<CalculationsRegistry> holder;
    private int maxId;

    public static ListCalculationsRegistryRepository getInstance() {
        if (instance == null) {
            instance = new ListCalculationsRegistryRepository();
        }
        return instance;
    }

    private ListCalculationsRegistryRepository() {
        this.holder = new ArrayList<>();
        this.maxId = 0;
    }

    public List<CalculationsRegistry> getHolder() {
        return holder;
    }

    public int getMaxId() {
        return maxId;
    }

    @Override
    public Optional<CalculationsRegistry> create(CalculationsRegistry registry) {
        int id = ++maxId;
        final CalculationsRegistry registryWithId = registry.withId(id);
        holder.add(registryWithId);

        return Optional.of(registryWithId);
    }

    @Override
    public Optional<CalculationsRegistry> read(int id) throws CalculationRegistryNotFoundException {
        final CalculationsRegistry registry = findRegistryById(id);
        if (registry == null) {
            throw new CalculationRegistryNotFoundException();
        }

        return Optional.of(registry);
    }

    @Override
    public Optional<CalculationsRegistry> update(CalculationsRegistry registry) throws CalculationRegistryNotFoundException {
        final int id = registry.getId();
        final CalculationsRegistry savedRegistry = this.read(id).get();
        final int registryIndex = holder.indexOf(savedRegistry);
        holder.set(registryIndex, registry);

        return Optional.of(registry);
    }

    @Override
    public void delete(int id) throws CalculationRegistryNotFoundException {
        final CalculationsRegistry registry = this.read(id).get();
        holder.remove(registry);
    }

    private CalculationsRegistry findRegistryById(int id) {
        for (CalculationsRegistry registry : holder) {
            if (registry.getId().equals(id)) {
                return registry;
            }
        }
        return null;
    }

    public int size() {
        return getHolder().size();
    }

    public void clear() {
        holder.clear();
    }

    public static void resetInstance() {
        if (instance != null) {
            instance.clear();
            instance = null;
        }
    }
}