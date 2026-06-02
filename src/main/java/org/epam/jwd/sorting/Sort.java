package org.epam.jwd.sorting;

import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.List;

public interface Sort {

    void sort(Component text);

    default void printStructure(Component text) {
        if (!(text instanceof Composite composite)) {
            return;
        }

        List<Component> paragraphs = composite.getChildren();
        for (int i = 0; i < paragraphs.size(); i++) {
            Component paragraph = paragraphs.get(i);
            int sentenceCount = countSentences(paragraph);
            System.out.printf("Paragraph %d: %d sentences%n", i + 1, sentenceCount);

            if (paragraph instanceof Composite paraComp) {
                for (int j = 0; j < paraComp.getChildren().size(); j++) {
                    Component sentence = paraComp.getChild(j);
                    int wordCount = countWords(sentence);
                    System.out.printf("  Sentence %d: %d words%n", j + 1, wordCount);
                }
            }
        }
    }

    private int countSentences(Component paragraph) {
        if (paragraph instanceof Composite composite) {
            return composite.getChildren().size();
        }
        return 0;
    }

    private int countWords(Component sentence) {
        if (sentence instanceof Composite composite) {
            return composite.getChildren().size();
        }
        return 0;
    }
}
