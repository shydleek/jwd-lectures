package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;
import org.epam.jwd.observer.RepositoryObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPlainRepository implements PlainRepository {

    private static InMemoryPlainRepository instance;

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryPlainRepository.class);

    private final List<Plain> holder;
    private final List<RepositoryObserver> observers;

    public static InMemoryPlainRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryPlainRepository();
        }
        return instance;
    }

    private InMemoryPlainRepository() {
        this.holder = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public Plain create(Plain plain) {
        holder.add(plain);
        notifyObserversOnCreate(plain);
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
        notifyObserversOnUpdate(plain);
        return oldPlain;
    }

    @Override
    public void delete(int id) {
        holder.remove(id);
        notifyObserversOnDelete(id);
    }

    public void attach(RepositoryObserver observer) {
        observers.add(observer);
    }

    public void detach(RepositoryObserver observer) {
        observers.remove(observer);
    }

    private void notifyObserversOnCreate(Plain plain) {
        for (RepositoryObserver observer : observers) {
            observer.onPlainCreated(plain);
        }
    }

    private void notifyObserversOnUpdate(Plain plain) {
        for (RepositoryObserver observer : observers) {
            observer.onPlainUpdated(plain);
        }
    }

    private void notifyObserversOnDelete(int id) {
        for (RepositoryObserver observer : observers) {
            Plain plain = holder.get(id);
            observer.onPlainDeleted(plain);
        }
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