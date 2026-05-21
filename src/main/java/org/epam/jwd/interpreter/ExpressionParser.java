package org.epam.jwd.interpreter;

public interface ExpressionParser {

    Expression parse(String expression);

    static ExpressionParser reversePolishNotation() {
        return ReversePolishNotationExpressionParser.getInstance();
    }
}
