package org.epam.jwd.util;

import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;
import org.epam.jwd.model.Leaf;
import org.epam.jwd.model.LeafType;

public class TextRestorer {

    public static String restore(Component component) {
        if (component instanceof Leaf leaf) {
            return leaf.getContent();
        } else if (component instanceof Composite composite) {
            StringBuilder sb = new StringBuilder();

            for (Component child : composite.getChildren()) {
                String childText = restore(child);

                if (child instanceof Leaf leaf) {
                    LeafType type = leaf.getType();

                    if (type == LeafType.WORD_SYMBOL) {
                        sb.append(childText);
                    } else if (type == LeafType.PUNCTUATION) {
                        if (!sb.isEmpty() && sb.charAt(sb.length() - 1) == ' ') {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        sb.append(childText);
                    } else if (type == LeafType.WHITESPACE) {
                        if (!sb.isEmpty() && sb.charAt(sb.length() - 1) != ' ') {
                            sb.append(' ');
                        }
                    } else {
                        sb.append(childText);
                    }
                } else {
                    if (!sb.isEmpty() && sb.charAt(sb.length() - 1) != ' ' &&
                            !childText.startsWith(" ") && !childText.startsWith(".") &&
                            !childText.startsWith(",") && !childText.startsWith("!")) {
                        sb.append(' ');
                    }
                    sb.append(childText);
                }
            }

            String result = sb.toString();
            result = result.replaceAll("\\( ", "(");
            result = result.replaceAll("\\[ ", "[");
            result = result.replaceAll("\\{ ", "{");

            return result.trim();
        }

        return "";
    }
}