package org.epam.jwd.repository;

import org.epam.jwd.model.CalculationsHolder;
import org.epam.jwd.model.Plain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CalculationsRepository {

    private static CalculationsRepository instance;

    private static final Logger LOG = LoggerFactory.getLogger(CalculationsRepository.class);

    private final PlainRepository plains;
    private final List<CalculationsHolder> calculations;

    private CalculationsRepository(PlainRepository plains) {
        this.plains = plains;
        this.calculations = new ArrayList<>();
    }

    public static CalculationsRepository getInstance() {
        if (instance == null) {
            instance = new CalculationsRepository(PlainRepository.getInstance());
        }
        return instance;
    }

    public List<CalculationsHolder> getCalculations() {
        return calculations;
    }

    public void create(CalculationsHolder calculation) {
        calculations.add(calculation);
    }

    public CalculationsHolder read(int id) {
        final Plain plain = plains.read(id);
        int index = findPlainIndex(plain);
        return calculations.get(index);
    }

    public void update(int id, CalculationsHolder calculation) {
        final Plain plain = plains.read(id);
        int index = findPlainIndex(plain);
        calculations.set(index, calculation);
    }

    public void delete(int id) {
        final Plain plain = plains.read(id);
        int index = findPlainIndex(plain);
        calculations.remove(index);
    }

    private int findPlainIndex(Plain plain) {
        for (int i = 0; i < plains.size(); i++) {
            Plain p = plains.getHolder().get(i);
            if (p == null) continue;
            if (p.equals(plain)) return i;
        }

        return -1;
    }

    public CalculationsHolder findCalculationByPlain(Plain plain) {
        int index = findPlainIndex(plain);

        if (index == -1) {
            return null;
        }

        return calculations.get(index);
    }

    public int size() {
        return getCalculations().size();
    }

    public void printAll() {
        if (plains.getHolder().isEmpty()) {
            LOG.info("No calculations stored");
        } else {
            for (int i = 0; i < plains.size(); i++) {
                System.out.println(plains.getHolder().get(i) + " -> " + calculations.get(i));
            }
        }
    }
}