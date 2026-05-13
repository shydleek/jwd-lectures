package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.Comparator;

public class SortByIdWithComparator implements Comparator<Plain> {
    @Override
    public int compare(Plain firstPlain, Plain secondPlain) {
        return firstPlain.getId().compareTo(secondPlain.getId());
    }

    @Override
    public Comparator<Plain> reversed() {
        return Comparator.super.reversed();
    }
}
