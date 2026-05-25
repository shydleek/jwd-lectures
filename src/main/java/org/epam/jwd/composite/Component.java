package org.epam.jwd.composite;

public interface Component {

    void add(Component c);

    void remove(Component c);

    Object getChild(int index);
}
