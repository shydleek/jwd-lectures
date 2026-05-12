package org.epam.jwd.sorting;

import java.util.Comparator;
import java.util.List;

public interface Sorting<Plain> extends Comparator<Plain> {
    List<Plain> sort(List<Plain> plains);
}