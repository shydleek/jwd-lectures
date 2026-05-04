package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;

import java.util.ArrayList;

public class InMemoryPlainRepository implements PlainRepository {

    private ArrayList<Plain> holder;

    public InMemoryPlainRepository(ArrayList<Plain> holder) {
        this.holder = holder;
    }

    @Override
    public Plain create(Plain plain) {
        return null;
    }

    @Override
    public Plain read(int id) {
        return null;
    }

    @Override
    public Plain update(Plain plain) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}