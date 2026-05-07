package org.epam.jwd.repository;

import org.epam.jwd.holder.CalculationsHolder;
import org.epam.jwd.model.Plain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.lang.reflect.Field;

public class InMemoryPlainRepositoryTest {

    private InMemoryPlainRepository repo = InMemoryPlainRepository.getInstance();
    private static final int ID = 0;
    private static final int SIZE_AFTER_DELETE = 0;

    @Mock
    private Plain mockPlain;

    @Mock
    private Plain newMockPlain;

    @Before
    public void resetSingleton() throws Exception {
        Field instance = InMemoryPlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void save_shouldSavePlainToTheRepoAndReturnThatPlain_whenPlainIsValid() {
        Assert.assertEquals(repo.create(mockPlain), mockPlain);
    }

    @Test
    public void read_shouldReadCorrectPlainAndReturnThatPlain_whenIdIsValid() {
        repo.create(mockPlain);

        Assert.assertEquals(repo.read(ID), mockPlain);
    }

    @Test
    public void update_shouldUpdatePlainInTheRepoAndReturnOldPlain_whenNewPlainIsValid() {
        repo.create(mockPlain);

        Assert.assertEquals(repo.update(0, newMockPlain), mockPlain);
    }

    @Test
    public void delete_shouldDeletePlainFromTheRepo_whenIdIsValid() {
        repo.create(mockPlain);
        repo.delete(ID);

        Assert.assertEquals(SIZE_AFTER_DELETE, repo.size());
    }
}
