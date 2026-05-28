package org.epam.jwd.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfixToReversePolishNotationConverterTest {

    private final InfixToReversePolishNotationConverter converter = InfixToReversePolishNotationConverter.getInstance();

    @ParameterizedTest
    @CsvSource({
            "~6&9|(3&4), 6 ~ 9 & 3 4 & |",
            "5(1&2&(3|(4&(^5|6&47)|3)|2)|1), 5 1 2 & 3 4 5 ^ 6 | 47 & & 3 | | 2 | & 1 |",
            "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78, 71 ~ 2 3 & 3 2 1 2 >> & 2 | | 2 & | 10 | 2 & & 78 |",
            "(~5|1&2<<(2|5>>2&71))|1200, 5 ~ 1 | 2 2 5 2 >> | 71 & << & 1200 |",
            "-(1 + 5) >>> 9, 1 5 + ~ 9 >>>",
            "5*-2, 5 2 ~ *"
    })
    public void infixToRPN_shouldReturnStringInReversePolishNotation(String lexeme, String expectedRPN){
        String actualRPN = converter.infixToRPN(lexeme);

        assertEquals(expectedRPN, actualRPN);
    }
}
