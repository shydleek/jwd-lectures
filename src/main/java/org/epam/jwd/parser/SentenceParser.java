package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.composite.Component;
import org.epam.jwd.composite.Composite;
import org.epam.jwd.converter.InfixToReversePolishNotationConverter;
import org.epam.jwd.interpreter.Expression;
import org.epam.jwd.interpreter.ExpressionParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends Parser {

    private static SentenceParser instance;

    private static final String REGEX = " ";
    private static final Pattern NUMBER = Pattern.compile("\\d+");
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

        String[] lexemesOfSentence = content.split(REGEX);

        for (String lexeme : lexemesOfSentence) {

            Matcher matcher = NUMBER.matcher(lexeme);

            if (matcher.find()) {
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