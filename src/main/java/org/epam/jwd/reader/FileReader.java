package org.epam.jwd.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {

    private static FileReader instance;

    private FileReader() {
    }

    public static FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }

    public List<String> readAllLinesFromFile(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }
}