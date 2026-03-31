package org.epam.jwd.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final String PROG_START = "Program start!";

    private static final String PROG_END = "Program end!";

    public static void main(String[] args) {
        LOG.trace(PROG_START);

        LOG.info("Hello and welcome!");

        LOG.trace(PROG_END);
    }
}