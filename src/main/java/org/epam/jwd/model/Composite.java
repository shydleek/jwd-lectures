package org.epam.jwd.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Composite implements Component {

    private final List<Component> components = new LinkedList<>();

    @Override
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        for (Component component : components) {
            sb.append(component.getContent());
        }
        return sb.toString();
    }

    @Override
    public void add(Component c) {
        components.add(c);
    }

    @Override
    public void remove(Component c) {
        components.remove(c);
    }

    @Override
    public Component getChild(int index) {
        return components.get(index);
    }

    public List<Component> getChildren() {
        return components;
    }

    @Override
    public String toString() {
        return "[" + components.stream()
                .map(Component::toString)
                .collect(Collectors.joining(", ")) + "]";
    }

}