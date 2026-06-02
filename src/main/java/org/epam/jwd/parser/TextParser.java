package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    private static TextParser instance;

    private static final String PARAGRAPH_REGEX = "(?m)^ {4}.*$";
    private static final Pattern PARAGRAPH_PATTERN = Pattern.compile(PARAGRAPH_REGEX, Pattern.MULTILINE);

    private static final Logger LOG = LogManager.getLogger(TextParser.class);

    public static TextParser getInstance() {
        if (instance == null) {
            instance = new TextParser();
        }
        return instance;
    }

    @Override
    public Component parse(String content) {
        System.out.println("Text parser");

        Composite text = new Composite();

        if (content == null || content.trim().isEmpty()) {
            return text;
        }

        Matcher matcher = PARAGRAPH_PATTERN.matcher(content);

        while (matcher.find()) {
            String paragraph = matcher.group();
            LOG.info(paragraph);
            text.add(next.parse(paragraph));
        }

        return text;
    }
}