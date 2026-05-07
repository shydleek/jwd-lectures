package org.epam.jwd.specification;

import org.epam.jwd.holder.CalculationsRegistry;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.observer.RepositorySaveListener;
import org.epam.jwd.repository.InMemoryPlainRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

public class PerpendicularOySpecificationTest {

    InMemoryPlainRepository repo = InMemoryPlainRepository.getInstance();
    CalculationsRegistry recorder = CalculationsRegistry.getInstance();
    RepositorySaveListener saveListener = new RepositorySaveListener();

    Plain perpOxPlain = new Plain(
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, new BigDecimal("5"), BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("5"))
    );
    Plain perpOyPlain = new Plain(
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(new BigDecimal("5"), BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("5"))
    );
    Plain perpOzPlain = new Plain(
            new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(new BigDecimal("5"), BigDecimal.ZERO, BigDecimal.ZERO),
            new Point3d(BigDecimal.ZERO, new BigDecimal("5"), BigDecimal.ZERO)
    );

    @Before
    public void resetInMemoryPlainRepositorySingleton() throws Exception {
        Field instance = InMemoryPlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void resetCalculationsRegistrySingleton() throws Exception {
        Field instance = CalculationsRegistry.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void setUp() {
        repo.events.subscribe("save", saveListener);
        repo.create(perpOxPlain);
        repo.create(perpOyPlain);
        repo.create(perpOzPlain);
    }

    @Test
    public void sort_shouldReturnSortedList() {
        Specification<Plain> perpOySpec = new PerpendicularOySpecification(recorder);
        List<Plain> actualPlains = repo.findBySpecification(perpOySpec);

        Assert.assertEquals(perpOyPlain, actualPlains.getFirst());
    }
}