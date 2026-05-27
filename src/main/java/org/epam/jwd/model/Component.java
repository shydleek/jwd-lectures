package org.epam.jwd.model;

public interface Component {

    String getContent();

    void add(Component c);

    void remove(Component c);

    Component getChild(int index);
}
