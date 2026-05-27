package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;
import org.epam.jwd.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface PlainRepository extends Repository<Plain> {

    List<Plain> findBySpecification(Specification<Plain> specification);

    void sort(Comparator<Plain> c);
}