package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends Parser {

    private static ParagraphParser instance;
    private static final String SENTENCE_REGEX = "[A-Z].*?(?<=\\.{3}|[.!?])(?:\\s+|$)";
    private static final Pattern SENTENCE_PATTERN = Pattern.compile(SENTENCE_REGEX);

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

        Matcher matcher = SENTENCE_PATTERN.matcher(content);

        while (matcher.find()) {
            String sentence = matcher.group();
            LOG.info(sentence);
            paragraph.add(next.parse(sentence.trim()));
        }

        return paragraph;
    }
}