package org.epam.jwd.holder;

import org.epam.jwd.math.PlainCalculator;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CalculationsRegistryTest {

    private CalculationsRegistry registry = CalculationsRegistry.getInstance();
    private static final int SIZE = 1;

    @Mock
    private Plain mockPlain;

    @Mock
    private Plain newMockPlain;

    @Mock
    private CalculationsHolder mockCalculation;

    @Mock
    private CalculationsHolder newMockCalculation;

    @Before
    public void resetSingleton() throws Exception {
        Field instance = CalculationsRegistry.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void save_shouldAddPlainAndCalculationAsMockToTheList_always() {
        registry.save(mockPlain, mockCalculation);

        Assert.assertEquals(SIZE, registry.size());
        Assert.assertTrue(registry.contains(mockPlain));
    }

    @Test
    public void save_shouldAddPlainAndCalculationToTheList_always() {
        final PlainCalculator calculator = PlainCalculator.getInstance();

        registry.save(initPlain(), calculator.calculate(initPlain()));

        Assert.assertEquals(SIZE, registry.size());
        Assert.assertTrue(registry.contains(initPlain()));
    }

    @Test
    public void update_shouldUpdatePlainAndCalculation_always() {
        registry.save(mockPlain, mockCalculation);

        registry.update(newMockPlain, mockPlain, newMockCalculation);

        Assert.assertEquals(SIZE, registry.size());
        Assert.assertTrue(registry.contains(newMockPlain));
    }

    @Test
    public void delete_shouldDeletePlainAndCalculation_always() {
        registry.save(mockPlain, mockCalculation);

        registry.delete(mockPlain);

        Assert.assertFalse(registry.contains(newMockPlain));
    }

    private Plain initPlain() {
        return new Plain(
                new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                new Point3d(BigDecimal.ZERO, new BigDecimal("5"), BigDecimal.ZERO),
                new Point3d(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("5"))
        );
    }
}
