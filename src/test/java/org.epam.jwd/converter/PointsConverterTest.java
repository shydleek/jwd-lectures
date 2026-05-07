package org.epam.jwd.converter;

import org.epam.jwd.holder.CalculationsRegistry;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.reader.FileReader;
import org.epam.jwd.repository.InMemoryPlainRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class PointsConverterTest {

    private final FileReader fileReader = FileReader.getInstance();
    private final PointsConverter reader = PointsConverter.getInstance();
    private final String correctFilePath = "txt/inputPoints.txt";
    private final String emptyFilePath = "txt/emptyFile.txt";

    @Before
    public void resetFileReaderSingleton() throws Exception {
        Field instance = FileReader.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Before
    public void resetPointsConverterSingleton() throws Exception {
        Field instance = CalculationsRegistry.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void convertListOfPoints_shouldReturnListOfPoint3DLists_whenFilePathIsCorrect() throws IOException {
        List<List<Point3d>> expectedList = initPlains();

        Path path = Path.of(correctFilePath);
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<Point3d>> actualList = reader.convertListOfPoints(inputLines);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void convertListOfPoints_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        Path path = Path.of(emptyFilePath);
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<Point3d>> actualList = reader.convertListOfPoints(inputLines);

        Assert.assertTrue(actualList.isEmpty());
    }

    private List<List<Point3d>> initPlains() {
        return List.of(
                List.of(
                        new Point3d(
                                new BigDecimal("1"),
                                new BigDecimal("3"),
                                new BigDecimal("5")
                        ),
                        new Point3d(
                                new BigDecimal("1"),
                                new BigDecimal("4"),
                                new BigDecimal("7")
                        ),
                        new Point3d(
                                new BigDecimal("-5"),
                                new BigDecimal("2"),
                                new BigDecimal("1")
                        )
                ),
                List.of(
                        new Point3d(
                                new BigDecimal("3.5"),
                                new BigDecimal("3.7"),
                                new BigDecimal("-3.5")
                        ),
                        new Point3d(
                                new BigDecimal("0"),
                                new BigDecimal("2"),
                                new BigDecimal("1")
                        ),
                        new Point3d(
                                new BigDecimal("4"),
                                new BigDecimal("5.4"),
                                new BigDecimal("34.32")
                        )
                )
        );
    }
}