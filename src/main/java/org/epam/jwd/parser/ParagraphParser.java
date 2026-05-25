package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.composite.Component;
import org.epam.jwd.composite.Composite;

public class ParagraphParser extends Parser {

    private static ParagraphParser instance;

    private static final String REGEX = "(?<=\\.{3}|[.!?])(?:\\s+|$)";
    private static final Logger LOG = LogManager.getLogger(ParagraphParser.class);

    public static ParagraphParser getInstance() {
        if (instance == null) {
            instance = new ParagraphParser();
        }
        return instance;
    }

    @Override
    public Component parse(String content) {
        System.out.println("Paragraph parser");

        Composite paragraph = new Composite();

        if (content == null || content.isEmpty()) {
            return paragraph;
        }

        String[] listOfSentences = content.split(REGEX);

        for (String sentence : listOfSentences) {
            LOG.info(sentence);
            paragraph.add(next.parse(sentence));
        }

        return paragraph;
    }
}