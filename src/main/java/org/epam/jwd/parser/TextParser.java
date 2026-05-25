package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.composite.Component;
import org.epam.jwd.composite.Composite;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends Parser {

    private static TextParser instance;

    private static final String REGEX = ".*? {4}";
    private static final Pattern PARAGRAPH = Pattern.compile(REGEX);
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

        Matcher matcher = PARAGRAPH.matcher(content);

        List<String> listOfParagraphs = new ArrayList<>();

        List<Integer> listOfParagraphStartIndex = new ArrayList<>();
        List<Integer> listOfParagraphEndIndex = new ArrayList<>();

        int counter = 0;

        while (matcher.find()) {
            if (counter > 0) {
                listOfParagraphEndIndex.add(matcher.start());
            }

            listOfParagraphStartIndex.add(matcher.end());
            counter++;
        }

        listOfParagraphEndIndex.add(matcher.regionEnd());

        for (int i = 0; i < listOfParagraphEndIndex.size(); i++) {
            listOfParagraphs.add(content.substring(listOfParagraphStartIndex.get(i),
                    listOfParagraphEndIndex.get(i)));
        }

        for (String paragraph : listOfParagraphs) {
            LOG.info(paragraph);
            text.add(next.parse(paragraph));
        }

        return text;
    }
}