package org.epam.jwd.repository;

import org.epam.jwd.exception.CalculationRegistryNotFoundException;
import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.CalculationsRegistry;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.observer.RepositoryDeleteListener;
import org.epam.jwd.observer.RepositorySaveListener;
import org.epam.jwd.observer.RepositoryUpdateListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ListCalculationsRegistryRepositoryTest {

    private ListPlainRepository repo;
    private ListCalculationsRegistryRepository calculations;
    private final RepositorySaveListener repositorySaveListener = new RepositorySaveListener();
    private final RepositoryUpdateListener repositoryUpdateListener = new RepositoryUpdateListener();
    private final RepositoryDeleteListener repositoryDeleteListener = new RepositoryDeleteListener();
    private static final int SIZE = 1;
    private static final int MAX_ID = 2;
    private static final int WRONG_ID = 2;

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
    public void setUp() {
        repo = ListPlainRepository.getInstance();
        calculations = ListCalculationsRegistryRepository.getInstance();
    }

    @After
    public void tearDown() {
        ListPlainRepository.resetInstance();
        ListCalculationsRegistryRepository.resetInstance();
    }

    @Test
    public void create_shouldAddCalculationAsMockToTheList_always() {
        repo.create(plain);

        Assert.assertEquals(SIZE, calculations.size());
    }

    @Test
    public void create_shouldAddCalculationToTheList_always() {
        repo.create(plain);

        Assert.assertEquals(SIZE, calculations.size());
    }

    @Test
    public void read_shouldReturnPlainById_always() {
        PlainCalculator calculator = PlainCalculator.getInstance();

        repo.create(plain);

        final int id = plain.getId();

        final CalculationsRegistry calculation = this.calculations.read(id).get();
        Assert.assertEquals(calculator.calculate(plain), calculation);
    }

    @Test
    public void update_shouldUpdateCalculation_always() {
        repo.create(plain);

        repo.update(newPlain);

        Assert.assertEquals(SIZE, calculations.size());
    }

    @Test
    public void delete_shouldDeleteCalculation_always() {
        repo.create(plain);

        repo.delete(plain.getId());

        Assert.assertNotEquals(SIZE, calculations.size());
    }

    @Test
    public void getMaxId_shouldReturnMaxIdOfHolder_always() {
        repo.create(plain);
        repo.delete(plain.getId());
        repo.create(plain);

        Assert.assertEquals(MAX_ID, calculations.getMaxId());
    }

    @Test(expected = CalculationRegistryNotFoundException.class)
    public void read_shouldThrowCalculationRegistryNotFoundException_whenIdIsWrong() {
        repo.create(plain);
        CalculationsRegistry readedRegistry = this.calculations.read(WRONG_ID).get();
    }
}
