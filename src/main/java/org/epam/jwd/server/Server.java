package org.epam.jwd.server;

import org.epam.jwd.model.Component;
import org.epam.jwd.parser.Parser;

public class Server {

    private Parser parser;

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public Parser getParser() {
        return parser;
    }

    public Component getParsedText(String text) {
        return parser.parse(text);
    }
}
