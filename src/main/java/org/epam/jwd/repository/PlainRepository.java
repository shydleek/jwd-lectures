package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;
import org.epam.jwd.specification.Specification;

import java.util.List;

public interface PlainRepository {

    Plain create(Plain plain);

    Plain read(int id);

    Plain update(int id, Plain plain);

    void delete(int id);

    List<Plain> findBySpecification(Specification<Plain> specification);
}