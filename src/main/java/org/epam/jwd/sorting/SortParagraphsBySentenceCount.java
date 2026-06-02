package org.epam.jwd.sorting;

import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.Comparator;

public enum SortParagraphsBySentenceCount implements Sort {

    INSTANCE,
    ;


    @Override
    public void sort(Component text) {
        if (text instanceof Composite composite) {
            composite.getChildren().sort(Comparator.comparingInt(p -> ((Composite) p).getChildren().size()));
        }
    }
}
