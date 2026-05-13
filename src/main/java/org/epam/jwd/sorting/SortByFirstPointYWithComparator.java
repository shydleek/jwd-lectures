package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.Comparator;

public class SortByFirstPointYWithComparator implements Comparator<Plain> {
    @Override
    public int compare(Plain firstPlain, Plain secondPlain) {
        return firstPlain.getA().getY().compareTo(secondPlain.getA().getY());
    }

    @Override
    public Comparator<Plain> reversed() {
        return Comparator.super.reversed();
    }
}
