package org.epam.jwd.sorting;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.repository.PlainRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class SortingByFirstPointXTest {

    private final PlainRepository firstRepo = PlainRepository.getInstance();
    private final Sorting sortingByFirstPointX = new SortingByFirstPointX();
    private final Plain firstPlain = new Plain(
            null,
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(new BigDecimal("4"), BigDecimal.ZERO, new BigDecimal("2")),
            new Point3d(BigDecimal.ZERO, new BigDecimal("4"), new BigDecimal("1")));
    private final Plain secondPlain =  new Plain(
            null,
            new Point3d(new BigDecimal("2"), BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, new BigDecimal("3"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("4")));
    private final Plain thirdPlain = new Plain(
            null,
            new Point3d(new BigDecimal("10"), BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, new BigDecimal("2"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("1")));

    @Before
    public void resetInMemoryPlainRepositorySingleton() throws Exception {
        Field instance = PlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void sort_shouldReturnSortedList() {
        firstRepo.create(secondPlain);
        firstRepo.create(thirdPlain);
        firstRepo.create(firstPlain);

        firstRepo.printAll();

        List<Plain> actualList = firstRepo.sort(sortingByFirstPointX);

        Assert.assertEquals(initSortedPlains(), actualList);
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
                        new Point3d(new BigDecimal("2"), BigDecimal.ZERO, BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, new BigDecimal("3"), BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("4"))),
                new Plain(
                        2,
                        new Point3d(new BigDecimal("10"), BigDecimal.ZERO, BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, new BigDecimal("2"), BigDecimal.ZERO),
                        new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("1")))
        );
    }
}