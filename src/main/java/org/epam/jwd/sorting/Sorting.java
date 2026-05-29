package org.epam.jwd.sorting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;
import org.epam.jwd.parser.LexemeParser;

import java.util.Comparator;
import java.util.List;

public enum Sorting {

    INSTANCE;


    public void sortParagraphsBySentenceCountAsc(Component text) {
        if (text instanceof Composite composite) {
            composite.getChildren().sort(
                    Comparator.comparingInt(p -> ((Composite) p).getChildren().size())
            );
        }
    }

//    public static void sortSentencesByWordLength(Component text) {
//        if (!(text instanceof Composite textComposite)) {
//            return;
//        }
//
//        // Проходим по каждому абзацу
//        for (Component paragraph : textComposite.getChildren()) {
//            sortSentencesInParagraphByWordLength(paragraph);
//        }
//    }
//
//    private static void sortSentencesInParagraphByWordLength(Component paragraph) {
//        if (!(paragraph instanceof Composite composite)) {
//            return;
//        }
//
//        List<Component> sentences = composite.getChildren();
//        sentences.sort(Comparator.comparingInt(Sorting::getLongestWordLength));
//    }
//
//    private static int getLongestWordLength(Component sentence) {
//        if (!(sentence instanceof Composite composite)) {
//            return 0;
//        }
//
//        int maxLength = 0;
//        for (Component word : composite.getChildren()) {
//            int wordLength = getWordLength(word);
//            if (wordLength > maxLength) {
//                maxLength = wordLength;
//                LOG.trace(maxLength);
//            }
//        }
//        return maxLength;
//    }

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

//    private static int getWordLength(Component word) {
//        if (!(word instanceof Composite composite)) {
//            return 0;
//        }
//        return composite.getChildren().size();
//    }

    public void printStructure(Component text) {
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
}