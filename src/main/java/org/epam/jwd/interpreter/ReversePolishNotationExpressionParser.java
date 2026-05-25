package org.epam.jwd.interpreter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ReversePolishNotationExpressionParser implements ExpressionParser {

    private static ReversePolishNotationExpressionParser instance;

    private static final Expression ADDITION_EXPRESSION = context -> context.push(
            context.pop() + context.pop());

    private static final Expression SUBSTRACTION_EXPRESSION = context -> {
        final int secondOperand = context.pop();
        context.push(context.pop() - secondOperand);
    };

    private static final Expression MULTIPLICATION_EXPRESSION = context -> context.push(
            context.pop() * context.pop());

    private static final Expression DIVISION_EXPRESSION = context -> {
        final int secondOperand = context.pop();
        context.push(context.pop() / secondOperand);
    };

    //побитовые операции коммутативны (похер на порядок, как для сложения и умножения)
    private static final Expression AND_EXPRESSION = context -> context.push(
            context.pop() & context.pop());

    private static final Expression OR_EXPRESSION = context -> context.push(
            context.pop() | context.pop());

    private static final Expression XOR_EXPRESSION = context -> context.push(
            context.pop() ^ context.pop());

    //сдвиги некоммутативны (порядок важен, как для разности и деления)

    private static final Expression LEFT_SHIFT_EXPRESSION = context -> {
        final int secondOperand = context.pop();
        context.push(context.pop() << secondOperand);
    };

    private static final Expression RIGHT_SHIFT_EXPRESSION = context -> {
        final int secondOperand = context.pop();
        context.push(context.pop() >> secondOperand);
    };

    private static final Expression UNSIGNED_RIGHT_SHIFT_EXPRESSION = context -> {
        final int secondOperand = context.pop();
        context.push(context.pop() >>> secondOperand);
    };

    //NOT(~) унарный оператор
    private static final Expression NOT_EXPRESSION = context -> context.push(~context.pop());

    public static ReversePolishNotationExpressionParser getInstance() {
        if (instance == null) {
            instance = new ReversePolishNotationExpressionParser();
        }
        return instance;
    }

    @Override
    public Expression parse(String expression) {
        List<Expression> expressions = new LinkedList<>();
        for (String lexeme : expression.split("[ \\t]+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            final char operator = lexeme.charAt(0);
            switch (operator) {
                case '+':
                    expressions.add(ADDITION_EXPRESSION);
                    break;
                case '-':
                    expressions.add(SUBSTRACTION_EXPRESSION);
                    break;
                case '*':
                    expressions.add(MULTIPLICATION_EXPRESSION);
                    break;
                case '/':
                    expressions.add(DIVISION_EXPRESSION);
                    break;
                case '&':
                    expressions.add(AND_EXPRESSION);
                    break;
                case '|':
                    expressions.add(OR_EXPRESSION);
                    break;
                case '^':
                    expressions.add(XOR_EXPRESSION);
                    break;
                case '<':
                    if (lexeme.equals("<<")) {
                        expressions.add(LEFT_SHIFT_EXPRESSION);
                        break;
                    }
                case '>':
                    switch (lexeme) {
                        case ">>":
                            expressions.add(RIGHT_SHIFT_EXPRESSION);
                            break;
                        case ">>>":
                            expressions.add(UNSIGNED_RIGHT_SHIFT_EXPRESSION);
                            break;
                    }
                    break;
                case '~':
                    expressions.add(NOT_EXPRESSION);
                    break;
                default:
                    final Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        expressions.add(new ExpressionNumber(scan.nextInt()));
                    }
                    break;
            }
        }
        return context -> {
            for (Expression exp : expressions) {
                exp.interpret(context);
            }
        };
    }
}
