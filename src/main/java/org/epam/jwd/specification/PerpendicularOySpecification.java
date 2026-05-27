package org.epam.jwd.specification;

import org.epam.jwd.model.CalculationsRegistry;
import org.epam.jwd.repository.ListCalculationsRegistryRepository;
import org.epam.jwd.model.Plain;

public class PerpendicularOySpecification implements Specification<Plain> {

    private final ListCalculationsRegistryRepository listCalculationsRegistryRepository;

    public PerpendicularOySpecification() {
        this.listCalculationsRegistryRepository = ListCalculationsRegistryRepository.getInstance();
    }

    @Override
    public boolean isSatisfiedBy(Plain plain) {
        int id = plain.getId();

        CalculationsRegistry registry = this.listCalculationsRegistryRepository.read(id).get();

        return registry.isPerpendicularToYAxis();
    }
}
