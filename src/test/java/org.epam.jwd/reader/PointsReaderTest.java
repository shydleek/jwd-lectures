package org.epam.jwd.reader;

import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class PointsReaderTest {

    final private FileReader fileReader = FileReader.getInstance();
    final private PointsReader reader = PointsReader.getInstance();

    @Test
    public void readListOfPoints_shouldReturnListOfPoint3DLists_whenFilePathIsCorrect() throws IOException {
        List<List<Point3d>> expectedList = List.of(
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

        Path path = Path.of("txt/inputPoints.txt");
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<Point3d>> actualList = reader.readListOfPoints(inputLines);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void readListOfPoints_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        Path path = Path.of("txt/emptyFile.txt");
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<Point3d>> actualList = reader.readListOfPoints(inputLines);

        Assert.assertTrue(actualList.isEmpty());
    }
}
