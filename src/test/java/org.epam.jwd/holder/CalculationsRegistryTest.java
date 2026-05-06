package org.epam.jwd.holder;

import org.epam.jwd.model.Plain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;

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
    public void save_shouldAddPlainAndCalculationToTheList_always() {
        registry.save(mockPlain, mockCalculation);

        Assert.assertEquals(SIZE, registry.size());
        Assert.assertTrue(registry.contains(mockPlain));
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
}
