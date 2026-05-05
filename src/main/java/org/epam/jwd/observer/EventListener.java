package org.epam.jwd.observer;

import org.epam.jwd.model.Plain;

public interface EventListener {
    void update(String eventType, Plain plain);
}