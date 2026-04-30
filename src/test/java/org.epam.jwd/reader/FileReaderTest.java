package org.epam.jwd.reader;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;

public class FileReaderTest {

    private final FileReader fileReader = FileReader.getInstance();

    @Test(expected = IOException.class)
    public void readAllLinesFromFile_shouldThrowIOException_whenFilePathIsNotCorrect() throws IOException {
        Path path = Path.of("inputCorrectCoefficientddd.txt");
        fileReader.readAllLinesFromFile(path);
    }
}
