package org.epam.jwd.repository;

import java.util.Optional;

public interface Repository<T> {

    Optional<T> create(T t);

    Optional<T> read(int id);

    Optional<T> update(T t);

    void delete(int id);
}