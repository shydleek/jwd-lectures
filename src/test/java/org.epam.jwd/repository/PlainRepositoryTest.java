package org.epam.jwd.repository;

import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class PlainRepositoryTest {

    private final PlainRepository repo = PlainRepository.getInstance();
    private static final int ID = 1;
    private static final int SIZE_AFTER_DELETE = 0;


    private final Plain plain = new Plain(
            1,
            new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1)));

    @Before
    public void resetInMemoryPlainRepositorySingleton() throws Exception {
        Field instance = PlainRepository.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void save_shouldSavePlainToTheRepoAndReturnThatPlain_whenPlainIsValid() {
        Assert.assertEquals(repo.create(plain), plain);
    }

    @Test
    public void read_shouldReadCorrectPlainAndReturnThatPlain_whenIdIsValid() {
        repo.create(plain);

        Assert.assertEquals(repo.read(ID), plain);
    }

    @Test
    public void update_shouldUpdatePlainInTheRepoAndReturnOldPlain_whenNewPlainIsValid() {
        repo.create(plain);

        Assert.assertEquals(repo.update(plain), plain);
    }

    @Test
    public void delete_shouldDeletePlainFromTheRepo_whenIdIsValid() {
        repo.create(plain);
        repo.delete(ID);

        Assert.assertEquals(SIZE_AFTER_DELETE, repo.size());
    }
}
