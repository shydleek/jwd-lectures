package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;
import org.epam.jwd.observer.EventManager;
import org.epam.jwd.sorting.SortingStrategy;
import org.epam.jwd.specification.Specification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPlainRepository implements PlainRepository {

    private static InMemoryPlainRepository instance;

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryPlainRepository.class);

    private final List<Plain> holder;
    public EventManager events;

    public static InMemoryPlainRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryPlainRepository();
        }
        return instance;
    }

    private InMemoryPlainRepository() {
        this.holder = new ArrayList<>();
        this.events = new EventManager("save", "update", "delete");
    }

    @Override
    public Plain create(Plain plain) {
        holder.add(plain);
        events.notify("save", plain, null);
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
        events.notify("update", plain, oldPlain);
        return oldPlain;
    }

    @Override
    public void delete(int id) {
        Plain plain = holder.get(id);
        holder.remove(id);
        events.notify("delete", null, plain);
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
    public List<Plain> sort(SortingStrategy sortingStrategy) {
        return sortingStrategy.sort(holder);
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
}