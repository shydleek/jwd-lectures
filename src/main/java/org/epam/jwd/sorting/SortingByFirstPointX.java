package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingByFirstPointX implements Sorting<Plain> {
    @Override
    public List<Plain> sort(List<Plain> plains) {
        List<Plain> sortedList = new ArrayList<>(plains);

        sortedList.sort(this::compare);

        return sortedList;
    }

    @Override
    public int compare(Plain firstPlain, Plain secondPlain) {
        return firstPlain.getA().getX().compareTo(secondPlain.getA().getX());
    }

    @Override
    public Comparator<Plain> reversed() {
        return Sorting.super.reversed();
    }
}