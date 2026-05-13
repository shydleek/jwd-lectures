package org.epam.jwd.repository;

import org.epam.jwd.specification.Specification;

import java.util.Comparator;
import java.util.List;

public interface Repository<T> {

    T create(T t);

    T read(int id);

    T update(T t);

    void delete(int id);

    List<T> findBySpecification(Specification<T> specification);

    void sort(Comparator<T> c);
}