package org.epam.jwd.model;

public class Leaf implements Component {

    private final LeafType type;
    private final String content;

    public Leaf(LeafType type, String content) {
        this.type = type;
        this.content = content;
    }

    public LeafType getType() {
        return type;
    }

    public String getContent() {
        return content;
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
    public Component getChild(int index) {
        throw new UnsupportedOperationException("Leaf has no children");
    }

//    @Override
//    public String toString() {
//        return "\nLeaf{" +
//                "type=" + type +
//                ", content='" + content + '\'' +
//                "}";
//    }

    @Override
    public String toString() {
        return content;
    }

}