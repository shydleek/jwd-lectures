package org.epam.jwd.parser;

import org.epam.jwd.model.Component;

public abstract class Parser {

    protected Parser next;

    public static Parser link(Parser first, Parser... chain) {
        Parser head = first;
        for (Parser nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public abstract Component parse(String content);
}