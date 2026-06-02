package org.epam.jwd.converter;

import java.util.*;

public class InfixToReversePolishNotationConverter {

    private static InfixToReversePolishNotationConverter instance;

    private final Set<String> OPERATORS;
    private final Map<String, Integer> PRECEDENCE;

    public InfixToReversePolishNotationConverter() {
        OPERATORS = getOperators();
        PRECEDENCE = getPrecedence();
    }

    private Map<String, Integer> getPrecedence() {
        final Map<String, Integer> precedence = new HashMap<>();

        precedence.put("~", 5);

        precedence.put("<<", 3);
        precedence.put(">>", 3);
        precedence.put(">>>", 3);

        precedence.put("*", 2);
        precedence.put("/", 2);

        precedence.put("+", 1);
        precedence.put("-", 1);

        precedence.put("&", 0);
        precedence.put("|", 0);
        precedence.put("^", 0);

        return precedence;
    }

    private Set<String> getOperators() {
        return new HashSet<>(Arrays.asList(
                "+", "-", "*", "/", "&", "|", "^", "<<", ">>", ">>>", "~"
        ));
    }

    public static InfixToReversePolishNotationConverter getInstance() {
        if (instance == null) {
            instance = new InfixToReversePolishNotationConverter();
        }
        return instance;
    }

    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        int counter = 0;
        int len = expression.length();

        while (counter < len) {
            char c = expression.charAt(counter);

            if (Character.isWhitespace(c)) {
                counter++;
                continue;
            }

            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();

                while (counter < len && (Character.isDigit(expression.charAt(counter))
                        || expression.charAt(counter) == '.')) {
                    num.append(expression.charAt(counter));
                    counter++;
                }
                tokens.add(num.toString());
                continue;
            }

            if (c == '<' && counter + 1 < len && expression.charAt(counter + 1) == '<') {
                tokens.add("<<");
                counter += 2;
                continue;
            }

            if (c == '>' && counter + 1 < len && expression.charAt(counter + 1) == '>') {
                if (counter + 2 < len && expression.charAt(counter + 2) == '>') {
                    tokens.add(">>>");
                    counter += 3;
                } else {
                    tokens.add(">>");
                    counter += 2;
                }
                continue;
            }

            tokens.add(String.valueOf(c));
            counter++;
        }
        return tokens;
    }

    public String infixToRPN(String infix) {
        List<String> tokens = tokenize(infix);
        Stack<String> operators = new Stack<>();
        List<String> output = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            if (isNumber(token)) {
                output.add(token);
            } else if (isUnaryOperator(token)) {
                operators.push(token);
            } else if (isOperator(token)) {
                // Определяем, является ли минус унарным
                if (token.equals("-") && (i == 0 || isLeftParenthesis(tokens.get(i - 1)) ||
                        isOperator(tokens.get(i - 1)))) {
                    operators.push("~"); // Преобразуем унарный минус в ~
                } else {
                    while (!operators.isEmpty() && isOperator(operators.peek()) &&
                            !operators.peek().equals("(") &&
                            getPrecedence(operators.peek()) >= getPrecedence(token)) {
                        output.add(operators.pop());
                    }
                    operators.push(token);
                }
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    output.add(operators.pop());
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                }
                if (!operators.isEmpty() && isUnaryOperator(operators.peek())) {
                    output.add(operators.pop());
                }
            }
        }

        while (!operators.isEmpty()) {
            String op = operators.pop();
            if (!op.equals("(") && !op.equals(")")) {
                output.add(op);
            }
        }

        return String.join(" ", output);
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return OPERATORS.contains(token);
    }

    private boolean isUnaryOperator(String token) {
        return token.equals("~");
    }

    private boolean isLeftParenthesis(String token) {
        return token.equals("(");
    }

    private int getPrecedence(String operator) {
        return PRECEDENCE.getOrDefault(operator, 0);
    }
}