package org.epam.jwd.specification;

import org.epam.jwd.holder.CalculationsHolder;
import org.epam.jwd.holder.CalculationsRegistry;
import org.epam.jwd.model.Plain;

import java.util.List;

public class PerpendicularOySpecification implements Specification<Plain>{

    private CalculationsHolder calculations;
    private CalculationsRegistry registry;

    public PerpendicularOySpecification(CalculationsRegistry registry) {
        this.registry = registry;
    }

    @Override
    public boolean isSatisfiedBy(Plain plain) {
        calculations = registry.findCalculationByPlain(plain);

        return calculations.isPerpendicularToYAxis();
    }
}
