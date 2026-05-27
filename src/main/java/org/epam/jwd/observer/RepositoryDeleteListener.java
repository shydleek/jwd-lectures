package org.epam.jwd.observer;

import org.epam.jwd.repository.ListCalculationsRegistryRepository;
import org.epam.jwd.repository.ListPlainRepository;

public class RepositoryDeleteListener implements EventListener {

    private final ListCalculationsRegistryRepository listCalculationsRegistryRepository;
    private final ListPlainRepository listPlainRepository;

    public RepositoryDeleteListener() {
        this.listCalculationsRegistryRepository = ListCalculationsRegistryRepository.getInstance();
        this.listPlainRepository = ListPlainRepository.getInstance();
        listPlainRepository.getEvents().subscribe("delete", this);
    }

    @Override
    public void update(String eventType, int id) {
        listCalculationsRegistryRepository.delete(id);
    }
}