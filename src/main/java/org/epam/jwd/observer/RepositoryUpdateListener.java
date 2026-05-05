package org.epam.jwd.observer;

import org.epam.jwd.holder.CalculationsHolder;
import org.epam.jwd.holder.CalculationsRegistry;
import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.Plain;

public class RepositoryUpdateListener implements EventListener {

    private final PlainCalculator plainCalculator;
    private final CalculationsRegistry calculationsRegistry;

    public RepositoryUpdateListener() {
        this.plainCalculator = PlainCalculator.getInstance();
        this.calculationsRegistry = CalculationsRegistry.getInstance();
    }

    @Override
    public void update(String eventType, Plain plain) {
        CalculationsHolder result = plainCalculator.calculate(plain);
        calculationsRegistry.update(plain, result);
    }
}
