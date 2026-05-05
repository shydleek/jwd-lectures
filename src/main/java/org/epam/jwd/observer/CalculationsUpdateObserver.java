package org.epam.jwd.observer;

import org.epam.jwd.holder.CalculationsHolder;
import org.epam.jwd.holder.CalculationsRecorder;
import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.Plain;

public class CalculationsUpdateObserver implements RepositoryObserver{

    private final PlainCalculator plainCalculator;
    private final CalculationsRecorder calculationsRecorder;

    public CalculationsUpdateObserver() {
        this.plainCalculator = PlainCalculator.getInstance();
        this.calculationsRecorder = CalculationsRecorder.getInstance();
    }

    @Override
    public void onPlainCreated(Plain plain) {
        CalculationsHolder result = plainCalculator.calculate(plain);
        calculationsRecorder.save(plain, result);
    }

    @Override
    public void onPlainUpdated(Plain plain) {
        CalculationsHolder result = plainCalculator.calculate(plain);
        calculationsRecorder.update(plain, result);
    }

    @Override
    public void onPlainDeleted(Plain plain) {
        calculationsRecorder.delete(plain);
    }
}
