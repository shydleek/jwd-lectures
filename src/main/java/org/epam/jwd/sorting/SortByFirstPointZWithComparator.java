package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.Comparator;

public class SortByFirstPointZWithComparator implements Comparator<Plain> {
    @Override
    public int compare(Plain firstPlain, Plain secondPlain) {
        return firstPlain.getA().getZ().compareTo(secondPlain.getA().getZ());
    }

    @Override
    public Comparator<Plain> reversed() {
        return Comparator.super.reversed();
    }
}
