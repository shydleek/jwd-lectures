package org.epam.jwd.observer;

import org.epam.jwd.model.CalculationsRegistry;
import org.epam.jwd.model.Plain;
import org.epam.jwd.repository.ListCalculationsRegistryRepository;
import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.repository.ListPlainRepository;

public class RepositoryUpdateListener implements EventListener {

    private final PlainCalculator plainCalculator;
    private final ListCalculationsRegistryRepository listCalculationsRegistryRepository;
    private final ListPlainRepository listPlainRepository;

    public RepositoryUpdateListener() {
        this.plainCalculator = PlainCalculator.getInstance();
        this.listCalculationsRegistryRepository = ListCalculationsRegistryRepository.getInstance();
        this.listPlainRepository = ListPlainRepository.getInstance();
        listPlainRepository.getEvents().subscribe("update", this);
    }

    @Override
    public void update(String eventType, int id) {
        final Plain plain = this.listPlainRepository.read(id).get();

        CalculationsRegistry registry = plainCalculator.calculate(plain);

        listCalculationsRegistryRepository.update(registry);
    }
}