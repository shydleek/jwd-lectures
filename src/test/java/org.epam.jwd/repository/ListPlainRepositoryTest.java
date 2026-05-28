package org.epam.jwd.repository;

import org.epam.jwd.exception.PlainNotFoundException;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point3d;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

public class ListPlainRepositoryTest {

    private ListPlainRepository repo;
    private static final int ID = 1;
    private static final int SIZE_AFTER_DELETE = 0;
    private static final int MAX_ID = 2;
    private static final int WRONG_ID = 2;

    private final Plain plain = new Plain(
            1,
            new Point3d(new BigDecimal(1), new BigDecimal(0), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(1), new BigDecimal(0)),
            new Point3d(new BigDecimal(0), new BigDecimal(0), new BigDecimal(1))
    );

    @Before
    public void setUp() {
        repo = ListPlainRepository.getInstance();
    }

    @After
    public void tearDown() {
        ListPlainRepository.resetInstance();
    }

    @Test
    public void save_shouldSavePlainToTheRepoAndReturnThatPlain_whenPlainIsValid() {
        Assert.assertEquals(repo.create(plain), Optional.of(plain));
    }

    @Test
    public void read_shouldReadCorrectPlainAndReturnThatPlain_whenIdIsValid() {
        repo.create(plain);

        Assert.assertEquals(repo.read(ID), Optional.of(plain));
    }

    @Test
    public void update_shouldUpdatePlainInTheRepoAndReturnOldPlain_whenNewPlainIsValid() {
        repo.create(plain);

        Assert.assertEquals(repo.update(plain), Optional.of(plain));
    }

    @Test
    public void delete_shouldDeletePlainFromTheRepo_whenIdIsValid() {
        repo.create(plain);
        repo.delete(ID);

        Assert.assertEquals(SIZE_AFTER_DELETE, repo.size());
    }

    @Test
    public void getMaxId_shouldReturnMaxIdOfRepo_always() {
        repo.create(plain);
        repo.delete(ID);
        repo.create(plain);

        Assert.assertEquals(MAX_ID, repo.getMaxId());
    }

    @Test(expected = PlainNotFoundException.class)
    public void read_shouldThrowPlainNotFoundException_whenIdIsWrong() {
        repo.create(plain);
        Plain readedPlain = this.repo.read(WRONG_ID).get();
    }
}
