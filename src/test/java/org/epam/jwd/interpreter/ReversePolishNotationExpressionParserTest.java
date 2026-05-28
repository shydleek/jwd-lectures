package org.epam.jwd.interpreter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationExpressionParserTest {

    private final ExpressionParser parser = ExpressionParser.reversePolishNotation();

    @ParameterizedTest
    @MethodSource("provideExpressionsAndResults")
    public void parse_shouldReturnExpressionAndCalculateResult(String expression, Number expectedNumber) {
        Expression actualExpression = parser.parse(expression);
        Number actualNumber = actualExpression.result();

        assertEquals(expectedNumber, actualNumber);
    }

    private static Stream<Arguments> provideExpressionsAndResults() {
        return Stream.of(
                Arguments.of("6 ~ 9 & 3 4 & |", 9),
                Arguments.of("5 1 2 & 3 4 5 ^ 6 | 47 & & 3 | | 2 | & 1 |", 1),
                Arguments.of("71 ~ 2 3 & 3 2 1 2 >> & 2 | | 2 & | 10 | 2 & & 78 |", 78),
                Arguments.of("5 ~ 1 | 2 2 5 2 >> | 71 & << & 1200 |", 1200),
                Arguments.of("1 5 + ~ 9 >>>", 8388607),
                Arguments.of("10 4 - 3 * 6 /", 3)
        );
    }
}
