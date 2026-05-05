package org.epam.jwd.observer;

import org.epam.jwd.model.Plain;

public interface RepositoryObserver {

    void onPlainCreated(Plain plain);

    void onPlainUpdated(Plain plain);

    void onPlainDeleted(Plain plain);
}