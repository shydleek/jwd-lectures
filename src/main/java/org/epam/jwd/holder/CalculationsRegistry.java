package org.epam.jwd.holder;

import org.epam.jwd.model.Plain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculationsRegistry {

    private static CalculationsRegistry instance;

    private static final Logger LOG = LoggerFactory.getLogger(CalculationsRegistry.class);

    private final List<Plain> plains;
    private final List<CalculationsHolder> calculations;

    private CalculationsRegistry() {
        this.plains = new ArrayList<>();
        this.calculations = new ArrayList<>();
    }

    public static CalculationsRegistry getInstance() {
        if (instance == null) {
            instance = new CalculationsRegistry();
        }
        return instance;
    }

    public void save(Plain plain, CalculationsHolder calculation) {
        plains.add(plain);
        calculations.add(calculation);
    }

    public void update(Plain plain, CalculationsHolder calculation) {
        int index = plains.indexOf(plain);
        if (index != -1) {
            calculations.set(index, calculation);
        } else {
            save(plain, calculation);
        }
    }

    public void delete(Plain plain) {
        int index = plains.indexOf(plain);
        if (index != -1) {
            plains.remove(index);
            calculations.remove(index);
        }
    }

    public void printAll() {
        if (plains.isEmpty()) {
            LOG.info("No calculations stored");
        } else {
            for (int i = 0; i < plains.size(); i++) {
                System.out.println(plains.get(i) + " -> " + calculations.get(i));
            }
        }
    }
}
