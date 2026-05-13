package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.Comparator;

public class SortByFirstPointXWithComparator implements Comparator<Plain> {
    @Override
    public int compare(Plain firstPlain, Plain secondPlain) {
        return firstPlain.getA().getX().compareTo(secondPlain.getA().getX());
    }

    @Override
    public Comparator<Plain> reversed() {
        return Comparator.super.reversed();
    }
}
