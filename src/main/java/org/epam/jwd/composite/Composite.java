package org.epam.jwd.composite;

import java.util.LinkedList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> components = new LinkedList<>();

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }
}