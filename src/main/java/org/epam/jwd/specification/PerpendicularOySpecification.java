package org.epam.jwd.specification;

import org.epam.jwd.model.CalculationsHolder;
import org.epam.jwd.repository.CalculationsRepository;
import org.epam.jwd.model.Plain;

public class PerpendicularOySpecification implements Specification<Plain> {

    private CalculationsHolder calculation;
    private final CalculationsRepository calculations;

    public PerpendicularOySpecification(CalculationsRepository calculations) {
        this.calculations = calculations;
    }

    @Override
    public boolean isSatisfiedBy(Plain plain) {
        calculation = calculations.findCalculationByPlain(plain);

        return calculation.isPerpendicularToYAxis();
    }
}
