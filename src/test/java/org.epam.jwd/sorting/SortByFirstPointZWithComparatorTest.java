package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.repository.ListPlainRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class SortByFirstPointZWithComparatorTest {

    private ListPlainRepository repo;
    private final SortByFirstPointZWithComparator sortingByFirstPointZ = new SortByFirstPointZWithComparator();
    private final Plain firstPlain = new Plain(
            null,
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(new BigDecimal("4"), BigDecimal.ZERO, new BigDecimal("2")),
            new Point3d(BigDecimal.ZERO, new BigDecimal("4"), new BigDecimal("1"))
    );

    private final Plain secondPlain = new Plain(
            null,
            new Point3d(new BigDecimal("2"), new BigDecimal("1"), new BigDecimal("2")),
            new Point3d(BigDecimal.ZERO, new BigDecimal("3"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("4"))
    );

    private final Plain thirdPlain = new Plain(
            null,
            new Point3d(new BigDecimal("10"), new BigDecimal("2"), new BigDecimal("10")),
            new Point3d(BigDecimal.ZERO, new BigDecimal("2"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("1"))
    );

    @Before
    public void setUp() {
        repo = ListPlainRepository.getInstance();
        repo.create(secondPlain);
        repo.create(thirdPlain);
        repo.create(firstPlain);
    }

    @After
    public void tearDown() {
        ListPlainRepository.resetInstance();
    }

    @Test
    public void sort_shouldReturnSortedList() {
        repo.sort(sortingByFirstPointZ);

        Assert.assertEquals(initSortedPlains(), repo.getHolder());
    }

    private List<Plain> initSortedPlains() {
        return List.of(
                new Plain(
                        3,
                        new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Point3d(new BigDecimal("4"), BigDecimal.ZERO, new BigDecimal("2")),
                        new Point3d(BigDecimal.ZERO, new BigDecimal("4"), new BigDecimal("1"))),
                new Plain(
                        1,
                        new Point3d(new BigDecimal("2"), new BigDecimal("1"), new BigDecimal("2")),
                        new Point3d(BigDecimal.ZERO, new BigDecimal("3"), BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("4"))),
                new Plain(
                        2,
                        new Point3d(new BigDecimal("10"), new BigDecimal("2"), new BigDecimal("10")),
                        new Point3d(BigDecimal.ZERO, new BigDecimal("2"), BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("1")))
        );
    }
}
