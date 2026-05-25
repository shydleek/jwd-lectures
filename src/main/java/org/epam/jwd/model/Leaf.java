package org.epam.jwd.model;

public class Leaf implements Component {

    private LeafType type;
    private String text;

    public Leaf(LeafType type, String text) {
        this.type = type;
        this.text = text;
    }

    public LeafType getType() {
        return type;
    }

    public void setType(LeafType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void add(Component c) {
        throw new UnsupportedOperationException("Cannot add to leaf");
    }

    @Override
    public void remove(Component c) {
        throw new UnsupportedOperationException("Cannot remove from leaf");
    }

    @Override
    public Object getChild(int index) {
        throw new UnsupportedOperationException("Leaf has no children");
    }
}