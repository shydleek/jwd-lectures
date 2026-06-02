package org.epam.jwd.sorting;

import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.Comparator;
import java.util.List;

public enum SortSentencesByLexemeLength implements Sort {

    INSTANCE,
    ;


    @Override
    public void sort(Component text) {
        if (!(text instanceof Composite composite)) {
            return;
        }

        for (Component paragraph : composite.getChildren()) {
            sortSentencesInParagraphByLexemeLength(paragraph);
        }
    }

    private void sortSentencesInParagraphByLexemeLength(Component paragraph) {
        if (!(paragraph instanceof Composite composite)) {
            return;
        }

        List<Component> sentences = composite.getChildren();
        sentences.sort(Comparator.comparingInt(this::getLongestLexemeLength));
    }

    private int getLongestLexemeLength(Component sentence) {
        if (!(sentence instanceof Composite composite)) {
            return 0;
        }

        int maxLength = 0;
        for (Component lexeme : composite.getChildren()) {
            int wordLength = getLexemeLength(lexeme);
            if (wordLength > maxLength) {
                maxLength = wordLength;
            }
        }

        return maxLength;
    }

    private int getLexemeLength(Component lexeme) {
        if (!(lexeme instanceof Composite composite)) {
            return 0;
        }

        return composite.getContent().length();
    }
}
