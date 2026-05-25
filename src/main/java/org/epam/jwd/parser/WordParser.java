package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;
import org.epam.jwd.model.Leaf;
import org.epam.jwd.model.LeafType;

public class WordParser extends Parser {

    private static WordParser instance;

    private static final Logger LOG = LogManager.getLogger(WordParser.class);

    public static WordParser getInstance() {
        if (instance == null) {
            instance = new WordParser();
        }
        return instance;
    }

    @Override
    public Component parse(String content) {
        System.out.println("Word parser");

        Composite word = new Composite();

        if (content == null || content.isEmpty()) {
            return word;
        }

        // Каждый символ - WORD_SYMBOL (включая дефисы и апострофы внутри слова)
        for (char c : content.toCharArray()) {
            LOG.info(c);
            word.add(new Leaf(LeafType.WORD_SYMBOL, String.valueOf(c)));
        }

        return word;
    }
}