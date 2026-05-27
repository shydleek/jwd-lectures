package org.epam.jwd.specification;

import org.epam.jwd.repository.ListCalculationsRegistryRepository;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.observer.RepositorySaveListener;
import org.epam.jwd.repository.ListPlainRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class PerpendicularOySpecificationTest {

    ListPlainRepository repo = ListPlainRepository.getInstance();
    ListCalculationsRegistryRepository calculations = ListCalculationsRegistryRepository.getInstance();
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
                    2,
                    new Point3d(BigDecimal.ZERO, new BigDecimal("-3"), BigDecimal.ZERO),
                    new Point3d(new BigDecimal("7"), new BigDecimal("-3"), BigDecimal.ZERO),
                    new Point3d(BigDecimal.ZERO, new BigDecimal("-3"), new BigDecimal("9"))
            )
    );

    @Before
    public void setUp() {
        repo.create(planeOX);
        repo.create(planeOY);
        repo.create(planeOZ);
    }

    @Test
    public void sort_shouldReturnSortedList() {
        Specification<Plain> perpOySpec = new PerpendicularOySpecification();
        List<Plain> actualList = repo.findBySpecification(perpOySpec);

        Assert.assertEquals(expectedList, actualList);
    }
}