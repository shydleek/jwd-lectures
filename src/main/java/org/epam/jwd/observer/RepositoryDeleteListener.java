package org.epam.jwd.observer;

import org.epam.jwd.repository.CalculationsRepository;
import org.epam.jwd.repository.PlainRepository;

public class RepositoryDeleteListener implements EventListener{

    private final CalculationsRepository calculationsRepository;
    private final PlainRepository repo;

    public RepositoryDeleteListener() {
        this.calculationsRepository = CalculationsRepository.getInstance();
        this.repo = PlainRepository.getInstance();
        repo.getEvents().subscribe("delete", this);
    }

    @Override
    public void update(String eventType, int id) {
        calculationsRepository.delete(id);
    }
}
