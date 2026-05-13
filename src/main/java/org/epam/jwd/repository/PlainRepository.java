package org.epam.jwd.repository;

import org.epam.jwd.exception.PlainNotFoundException;
import org.epam.jwd.model.Plain;
import org.epam.jwd.observer.EventManager;
import org.epam.jwd.sorting.Sorting;
import org.epam.jwd.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PlainRepository implements Repository<Plain> {

    private static PlainRepository instance;

    private static final Logger LOG = LoggerFactory.getLogger(PlainRepository.class);

    private final List<Plain> holder;
    private final EventManager events;
    private int maxId;

    public static PlainRepository getInstance() {
        if (instance == null) {
            instance = new PlainRepository();
        }
        return instance;
    }

    private PlainRepository() {
        this.holder = new ArrayList<>();
        this.events = new EventManager("save", "update", "delete");
        this.maxId = 0;
    }

    public EventManager getEvents() {
        return events;
    }

    public List<Plain> getHolder() {
        return holder;
    }

    @Override
    public Plain create(Plain plain) {
        int id = ++maxId;
        final Plain plainWithId = plain.withId(id);
        holder.add(plainWithId);

        events.notify("save", id);

        return plainWithId;
    }

    @Override
    public Plain read(int id) throws PlainNotFoundException {
        final Plain plain = findPlainById(id);

        if (plain == null) {
            throw new PlainNotFoundException();
        }

        return plain;
    }

    @Override
    public Plain update(Plain plain) throws PlainNotFoundException {
        final int id = plain.getId();
        final Plain savedPlain = read(id);
        final int plainIndex = holder.indexOf(savedPlain);
        holder.set(plainIndex, plain);

        events.notify("update", id);

        return plain;
    }

    @Override
    public void delete(int id) {
        try {
            final Plain plain = read(id);
            events.notify("delete", id);
            holder.remove(plain);
        } catch (PlainNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
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
    public List<Plain> sort(Sorting sorting) {
        return sorting.sort(holder);
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

    public void printAll() {
        if (holder.isEmpty()) {
            LOG.info("repo is empty");
        } else {
            for (int i = 0; i < holder.size(); i++) {
                System.out.println(i + ": " + holder.get(i));
            }
        }
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