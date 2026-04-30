package org.epam.jwd.reader;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

public class CoefficientsReaderTest {

    final private FileReader fileReader = FileReader.getInstance();
    final private CoefficientsReader reader = CoefficientsReader.getInstance();

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
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<BigDecimal>> actualList = reader.readListOfCoefficients(inputLines);

        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void readListOfCoefficients_shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        Path path = Path.of("txt/emptyFile.txt");
        List<String> inputLines = fileReader.readAllLinesFromFile(path);
        List<List<BigDecimal>> actualList = reader.readListOfCoefficients(inputLines);

        Assert.assertTrue(actualList.isEmpty());
    }
}
