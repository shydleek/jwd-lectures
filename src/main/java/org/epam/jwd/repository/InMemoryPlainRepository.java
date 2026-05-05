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
        holder.add(plain);
        int id = holder.size() - 1;
        return holder.get(id);
    }

    @Override
    public Plain read(int id) {
        return holder.get(id);
    }

    @Override
    public Plain update(int id, Plain plain) {
        Plain oldPlain = holder.get(id);
        holder.set(id, plain);
        return oldPlain;
    }

    @Override
    public void delete(int id) {
        holder.remove(id);
    }
}