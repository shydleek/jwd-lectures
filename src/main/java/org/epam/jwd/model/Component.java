package org.epam.jwd.model;

public interface Component {

    void add(Component c);

    void remove(Component c);

    Object getChild(int index);
}
