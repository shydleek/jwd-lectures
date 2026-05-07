package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;

import java.util.Comparator;
import java.util.List;

public interface Sorting extends Comparator<Plain> {
    List<Plain> sort(List<Plain> plains);
}
