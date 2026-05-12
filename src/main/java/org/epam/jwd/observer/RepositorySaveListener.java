package org.epam.jwd.observer;

import org.epam.jwd.model.CalculationsHolder;
import org.epam.jwd.model.Plain;
import org.epam.jwd.repository.CalculationsRepository;
import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.repository.PlainRepository;

public class RepositorySaveListener implements EventListener {

    private final PlainCalculator plainCalculator;
    private final CalculationsRepository calculationsRepository;
    private final PlainRepository repo;

    public RepositorySaveListener() {
        this.plainCalculator = PlainCalculator.getInstance();
        this.calculationsRepository = CalculationsRepository.getInstance();
        this.repo = PlainRepository.getInstance();
        repo.getEvents().subscribe("save", this);
    }

    @Override
    public void update(String eventType, int id) {
        final Plain plain = repo.read(id);
        CalculationsHolder result = plainCalculator.calculate(plain);
        calculationsRepository.create(result);
    }
}
