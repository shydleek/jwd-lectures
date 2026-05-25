package org.epam.jwd.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.jwd.composite.Component;
import org.epam.jwd.composite.Composite;
import org.epam.jwd.composite.Leaf;
import org.epam.jwd.composite.LeafType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LexemeParser extends Parser {

    private static LexemeParser instance;
    // Слово с дефисами (не разбивается) - буквы, цифры, дефисы, апострофы
    private static final Pattern HYPHENATED_APOSTROPHE_WORD =
            Pattern.compile("^[a-zA-Zа-яА-Я0-9]+(?:[-'][a-zA-Zа-яА-Я0-9]*)*$");

    // Только буквы и цифры (обычное слово)
    private static final Pattern SIMPLE_WORD = Pattern.compile("^[a-zA-Zа-яА-Я0-9]+$");

    // Числа
    private static final Pattern NUMBER = Pattern.compile("^\\d+$");

    // Операторы и знаки препинания
    private static final Pattern PUNCTUATION = Pattern.compile("^[.!?…,;:&|~^<>+\\-*/%=!]+$");

    // Пробелы
    private static final Pattern WHITESPACE = Pattern.compile("^\\s+$");
    private static final Logger LOG = LogManager.getLogger(LexemeParser.class);

    public static LexemeParser getInstance() {
        if (instance == null) {
            instance = new LexemeParser();
        }
        return instance;
    }

    @Override
    public Component parse(String content) {
        System.out.println("Lexeme parser");

        Composite lexeme = new Composite();

        if (content == null || content.isEmpty()) {
            return lexeme;
        }

        // Проверяем, является ли лексема пробелом
        if (WHITESPACE.matcher(content).matches()) {
            LOG.info(content);
            lexeme.add(new Leaf(LeafType.WHITESPACE, content));
            return lexeme;
        }

        // Слова с дефисами (не разбиваем)
        if (HYPHENATED_APOSTROPHE_WORD.matcher(content).matches()) {
            LOG.info(content);
            lexeme.add(next.parse(content)); // передаём в WordParser как единое целое
            return lexeme;
        }

        // Проверяем, является ли лексема только знаками препинания
        if (PUNCTUATION.matcher(content).matches()) {
            for (char c : content.toCharArray()) {
                LOG.info(c);
                lexeme.add(new Leaf(LeafType.PUNCTUATION, String.valueOf(c)));
            }
            return lexeme;
        }

        // 3. Обычные слова (без дефисов)
        if (SIMPLE_WORD.matcher(content).matches()) {
            LOG.info(content);
            lexeme.add(next.parse(content));
            return lexeme;
        }

        // 4. Числа
        if (NUMBER.matcher(content).matches()) {
            LOG.info(content);
            lexeme.add(next.parse(content));
            return lexeme;
        }

        // 5. Смешанные лексемы (например: "(five)", "content here'", "5(1&2&...")
        List<String> tokens = splitMixedLexeme(content);
        for (String token : tokens) {
            if (WHITESPACE.matcher(token).matches()) {
                LOG.info(token);
                lexeme.add(new Leaf(LeafType.WHITESPACE, token));
            } else if (HYPHENATED_APOSTROPHE_WORD.matcher(token).matches() ||
                    SIMPLE_WORD.matcher(token).matches() ||
                    NUMBER.matcher(token).matches()) {
                LOG.info(token);
                lexeme.add(next.parse(token));
            } else {
                // Отдельные символы пунктуации (скобки, операторы и т.д.)
                for (char c : token.toCharArray()) {
                    LOG.info(c);
                    lexeme.add(new Leaf(LeafType.PUNCTUATION, String.valueOf(c)));
                }
            }
        }
        return lexeme;
    }

    private List<String> splitMixedLexeme(String lexeme) {
        List<String> tokens = new ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < lexeme.length(); i++) {
            char c = lexeme.charAt(i);

            // Буква, цифра, дефис или апостроф внутри слова
            if (Character.isLetterOrDigit(c) || c == '-' || c == '\'') {
                // Если текущий накапливаемый токен - это слово, продолжаем
                current.append(c);
            } else {
                // Сохраняем накопленное слово
                if (!current.isEmpty()) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
                // Добавляем отдельный символ пунктуации
                tokens.add(String.valueOf(c));
            }
        }

        // Сохраняем последнее слово
        if (!current.isEmpty()) {
            tokens.add(current.toString());
        }

        return tokens;
    }
}