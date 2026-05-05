package org.epam.jwd.observer;

import org.epam.jwd.holder.CalculationsRegistry;
import org.epam.jwd.model.Plain;

public class RepositoryDeleteListener implements EventListener{

    private final CalculationsRegistry calculationsRegistry;

    public RepositoryDeleteListener() {
        this.calculationsRegistry = CalculationsRegistry.getInstance();
    }

    @Override
    public void update(String eventType, Plain plain) {
        calculationsRegistry.delete(plain);
    }
}
