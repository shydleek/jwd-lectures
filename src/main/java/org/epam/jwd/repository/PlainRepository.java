package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;

public interface PlainRepository {

    Plain create(Plain plain);

    Plain read(int id);

    Plain update(int id, Plain plain);

    void delete(int id);
}