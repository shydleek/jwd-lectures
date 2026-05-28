package org.epam.jwd.repository;

import org.epam.jwd.exception.PlainNotFoundException;
import org.epam.jwd.model.Plain;
import org.epam.jwd.observer.EventManager;
import org.epam.jwd.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ListPlainRepository implements PlainRepository {

    private static ListPlainRepository instance;

    private final List<Plain> holder;
    private final EventManager events;
    private int maxId;

    public static ListPlainRepository getInstance() {
        if (instance == null) {
            instance = new ListPlainRepository();
        }
        return instance;
    }

    private ListPlainRepository() {
        this.holder = new ArrayList<>();
        this.events = new EventManager("save", "update", "delete");
        this.maxId = 0;
    }

    public List<Plain> getHolder() {
        return holder;
    }

    public EventManager getEvents() {
        return events;
    }

    public int getMaxId() {
        return maxId;
    }

    @Override
    public Optional<Plain> create(Plain plain) {
        int id = ++maxId;
        final Plain plainWithId = plain.withId(id);
        holder.add(plainWithId);

        events.notify("save", id);

        return Optional.of(plainWithId);
    }

    @Override
    public Optional<Plain> read(int id) throws PlainNotFoundException {
        final Plain plain = findPlainById(id);

        if (plain == null) {
            throw new PlainNotFoundException();
        }

        return Optional.of(plain);
    }

    @Override
    public Optional<Plain> update(Plain plain) throws PlainNotFoundException {
        final int id = plain.getId();
        final Plain savedPlain = this.read(id).get();
        final int plainIndex = holder.indexOf(savedPlain);
        holder.set(plainIndex, plain);

        events.notify("update", id);

        return Optional.of(plain);
    }

    @Override
    public void delete(int id) throws PlainNotFoundException {
        final Plain plain = this.read(id).get();

        events.notify("delete", id);

        holder.remove(plain);
    }

    @Override
    public List<Plain> findBySpecification(Specification<Plain> specification) {
        List<Plain> result = new ArrayList<>();

        for (Plain plain : holder) {
            if (specification.isSatisfiedBy(plain)) {
                result.add(plain);
            }
        }

        return result;
    }

    @Override
    public void sort(Comparator<Plain> comparator) {
        holder.sort(comparator);
    }

    private Plain findPlainById(int id) {
        for (Plain plain : holder) {
            if (plain.getId().equals(id)) {
                return plain;
            }
        }
        return null;
    }

    public int size() {
        return holder.size();
    }

    public void clear() {
        holder.clear();
    }

    public static void resetInstance() {
        if (instance != null) {
            instance.clear();
            instance = null;
        }
    }
}