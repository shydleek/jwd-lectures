package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.converter.InfixToReversePolishNotationConverter;
import org.epam.jwd.interpreter.Expression;
import org.epam.jwd.interpreter.ExpressionParser;
import org.epam.jwd.model.Component;
import org.epam.jwd.model.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {

    private static SentenceParser instance;

    public static final String LEXEME_REGEX = "\\S+";
    private static final Pattern LEXEME_PATTERN = Pattern.compile(LEXEME_REGEX);

    public static final String NUMBER_REGEX = "\\d+";
    private static final Pattern NUMBER_PATTERN = Pattern.compile(NUMBER_REGEX);

    private static final Logger LOG = LogManager.getLogger(SentenceParser.class);

    private final ExpressionParser parser = ExpressionParser.reversePolishNotation();
    private final InfixToReversePolishNotationConverter converter = InfixToReversePolishNotationConverter.getInstance();

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    @Override
    public Component parse(String content) {
        System.out.println("Sentence parser");

        Composite sentence = new Composite();

        if (content == null || content.isEmpty()) {
            return sentence;
        }

        Matcher lexemeMatcher = LEXEME_PATTERN.matcher(content);

        while (lexemeMatcher.find()) {
            String lexeme = lexemeMatcher.group();

            Matcher numberMatcher = NUMBER_PATTERN.matcher(lexeme);
            if (numberMatcher.find()) {
                String expressionRPN = converter.infixToRPN(lexeme);
                Expression expression = parser.parse(expressionRPN);
                LOG.info(expression.result());
                sentence.add(next.parse(expression.result().toString()));
                continue;
            }

            LOG.info(lexeme);
            sentence.add(next.parse(lexeme));
        }

        return sentence;
    }
}