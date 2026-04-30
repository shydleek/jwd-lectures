package org.epam.jwd.reader;

import org.epam.jwd.model.Point3d;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class FileReaderTest {

    private final FileReader fileReader = FileReader.getInstance();

    @Test(expected = IOException.class)
    public void readListOfCoefficients_shouldThrowIOException_whenFile() throws IOException {
        Path path = Path.of("inputCorrectCoefficientddd.txt");
        fileReader.readLinesFromFile(path);
    }

    @Test(expected = IOException.class)
    public void readListOfPoints_shouldThrowIOException() throws IOException {
        Path path = Path.of("inputCorrectCoefficientddd.txt");
        fileReader.readLinesFromFile(path);
    }

    @Test
    public void readListOfCoefficients_shouldReturnListOfBigDecimalLists_whenFilePathIsCorrect() throws IOException {
        List<List<BigDecimal>> expectedList = List.of(
                List.of(
                        new BigDecimal("14.5"),
                        new BigDecimal("1.0"),
                        new BigDecimal("1.0"),
                        new BigDecimal("13.2")
                ),
                List.of(
                        new BigDecimal(120),
                        new BigDecimal(39),
                        new BigDecimal(130),
                        new BigDecimal(1849)
                ),
                List.of(
                        new BigDecimal("1e2000"),
                        new BigDecimal(2),
                        new BigDecimal(3),
                        new BigDecimal(4)
                )
        );

        Path path = Path.of("txt/inputCoefficients.txt");
        List<String> inputLines = fileReader.readLinesFromFile(path);
        List<List<BigDecimal>> actualList = fileReader.readListOfCoefficients(inputLines);

        Assert.assertEquals(expectedList, actualList);
    }

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
        List<String> inputLines = fileReader.readLinesFromFile(path);
        List<List<Point3d>> actualList = fileReader.readListOfPoints(inputLines);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void readListOfCoefficients_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        Path path = Path.of("emptyFile.txt");
        List<String> inputLines = fileReader.readLinesFromFile(path);
        List<List<BigDecimal>> actualList = fileReader.readListOfCoefficients(inputLines);

        Assert.assertTrue(actualList.isEmpty());
    }

    @Test
    public void readListOfPoints_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        Path path = Path.of("emptyFile.txt");
        List<String> inputLines = fileReader.readLinesFromFile(path);
        List<List<Point3d>> actualList = fileReader.readListOfPoints(inputLines);

        Assert.assertTrue(actualList.isEmpty());
    }
}
