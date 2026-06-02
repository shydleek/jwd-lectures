package org.epam.jwd.sorting;

import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;
import org.epam.jwd.model.Leaf;
import org.epam.jwd.model.LeafType;

import java.util.Comparator;
import java.util.List;

public enum SortSentencesByWordLength implements Sort {

    INSTANCE,
    ;


    @Override
    public void sort(Component text) {
        if (!(text instanceof Composite composite)) {
            return;
        }

        for (Component paragraph : composite.getChildren()) {
            sortSentencesInParagraphByWordLength(paragraph);
        }
    }

    private void sortSentencesInParagraphByWordLength(Component paragraph) {
        if (!(paragraph instanceof Composite composite)) {
            return;
        }

        List<Component> sentences = composite.getChildren();
        sentences.sort(Comparator.comparingInt(this::getLongestWordLengthInSentence));
    }

    private int getLongestWordLengthInSentence(Component sentence) {
        if (!(sentence instanceof Composite composite)) {
            return 0;
        }

        int maxLength = 0;
        for (Component lexeme : composite.getChildren()) {
            int wordLength = extractWordLength(lexeme);
            if (wordLength > maxLength) {
                maxLength = wordLength;
            }
        }
        return maxLength;
    }

    private int extractWordLength(Component lexeme) {
        if (!(lexeme instanceof Composite composite)) {
            return 0;
        }


        for (Component child : composite.getChildren()) {
            if (child instanceof Composite possibleWord) {
                int wordLength = getWordLength(possibleWord);
                if (wordLength > 0) {
                    return wordLength;
                }
            }
        }
        return 0;
    }

    private int getWordLength(Component word) {
        if (!(word instanceof Composite composite)) {
            return 0;
        }

        int length = 0;
        for (Component child : composite.getChildren()) {
            if (child instanceof Leaf leaf && leaf.getType() == LeafType.WORD_SYMBOL) {
                length++;
            }
        }

        return length;
    }
}
