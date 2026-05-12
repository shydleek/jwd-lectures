package org.epam.jwd.specification;

import org.epam.jwd.repository.CalculationsRepository;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.observer.RepositorySaveListener;
import org.epam.jwd.repository.PlainRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class PerpendicularOzSpecificationTest {

    PlainRepository repo = PlainRepository.getInstance();
    CalculationsRepository calculations = CalculationsRepository.getInstance();
    RepositorySaveListener saveListener = new RepositorySaveListener();

    Plain planeOX = new Plain(
            1,
            new Point3d(new BigDecimal("5"), BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(new BigDecimal("5"), new BigDecimal("8"), BigDecimal.ZERO),
            new Point3d(new BigDecimal("5"), BigDecimal.ZERO, new BigDecimal("6"))
    );

    Plain planeOY = new Plain(
            2,
            new Point3d(BigDecimal.ZERO, new BigDecimal("-3"), BigDecimal.ZERO),
            new Point3d(new BigDecimal("7"), new BigDecimal("-3"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, new BigDecimal("-3"), new BigDecimal("9"))
    );

    Plain planeOZ = new Plain(
            3,
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("10")),
            new Point3d(new BigDecimal("6"), BigDecimal.ZERO, new BigDecimal("10")),
            new Point3d(BigDecimal.ZERO, new BigDecimal("4"), new BigDecimal("10"))
    );

    List<Plain> expectedList = List.of(
            new Plain(
                    3,
                    new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("10")),
                    new Point3d(new BigDecimal("6"), BigDecimal.ZERO, new BigDecimal("10")),
                    new Point3d(BigDecimal.ZERO, new BigDecimal("4"), new BigDecimal("10"))
            )
    );

    @Before
    public void resetInMemoryPlainRepositorySingleton() throws Exception {
        Field instance = PlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void resetCalculationsRegistrySingleton() throws Exception {
        Field instance = CalculationsRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void setUp() {
        repo.create(planeOX);
        repo.create(planeOY);
        repo.create(planeOZ);
    }

    @Test
    public void sort_shouldReturnSortedList() {
        Specification<Plain> perpOzSpec = new PerpendicularOzSpecification(calculations);
        List<Plain> actualList = repo.findBySpecification(perpOzSpec);

        Assert.assertEquals(expectedList, actualList);
    }
}