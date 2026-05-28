package org.epam.jwd.reader;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FileReaderTest {

    private final FileReader fileReader = FileReader.getInstance();

    private final String incorrectFilePath = "inputCorrectCoefficientddd.txt";
    private final String correctFilePath = "txt/inputPoints.txt";

    @Test(expected = IOException.class)
    public void readAllLinesFromFile_shouldThrowIOException_whenFilePathIsNotCorrect() throws IOException {
        Path path = Path.of(incorrectFilePath);
        fileReader.readAllLinesFromFile(path);
    }

    @Test
    public void readAllLinesFromFile_shouldReturnAllLinesFromFile_whenFilePathIsCorrect() throws IOException {
        Path path = Path.of(correctFilePath);
        List<String> expectedList = initList();
        List<String> actualList = fileReader.readAllLinesFromFile(path);


        Assert.assertEquals(actualList, expectedList);
    }

    private List<String> initList() {
        return List.of(
                "1 3 5 1 4 7 -5 2 1",
                "e e f d a w t y j",
                "1 2 3 4 5",
                "3.5 3.7 -3.5 0 2 1 4 5.4 34.32"
        );
    }
}