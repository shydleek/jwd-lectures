package org.epam.jwd.repository;

import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.CalculationsHolder;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.observer.RepositoryDeleteListener;
import org.epam.jwd.observer.RepositorySaveListener;
import org.epam.jwd.observer.RepositoryUpdateListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CalculationsRepositoryTest {

    private final PlainRepository repo = PlainRepository.getInstance();
    private final CalculationsRepository registry = CalculationsRepository.getInstance();
    private final RepositorySaveListener repositorySaveListener = new RepositorySaveListener();
    private final RepositoryUpdateListener repositoryUpdateListener = new RepositoryUpdateListener();
    private final RepositoryDeleteListener repositoryDeleteListener = new RepositoryDeleteListener();
    private static final int SIZE = 1;

    private final Plain plain = new Plain(
            1,
            new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1))
    );

    private final Plain newPlain = new Plain(
            1,
            new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
            new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(1))
    );

    @Before
    public void resetCalculationsRepositorySingleton() throws Exception {
        Field instance = CalculationsRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void resetPlainRepositorySingleton() throws Exception {
        Field instance = PlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void create_shouldAddCalculationAsMockToTheList_always() {
        repo.create(plain);

        Assert.assertEquals(SIZE, registry.size());
    }

    @Test
    public void create_shouldAddCalculationToTheList_always() {
        repo.create(plain);

        Assert.assertEquals(SIZE, registry.size());
    }

    @Test
    public void read_shouldReturnPlainById_always() {
        PlainCalculator calculator = PlainCalculator.getInstance();

        repo.create(plain);

        final int id = plain.getId();

        final CalculationsHolder calculation = registry.read(id);
        Assert.assertEquals(calculator.calculate(plain), calculation);
    }

    @Test
    public void update_shouldUpdateCalculation_always() {
        repo.create(plain);

        repo.update(newPlain);

        Assert.assertEquals(SIZE, registry.size());
    }

    @Test
    public void delete_shouldDeleteCalculation_always() {
        repo.create(plain);

        repo.delete(plain.getId());

        Assert.assertNotEquals(SIZE, registry.size());
    }
}
