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

    public String readStringFromFile(Path filePlath) throws IOException {
        return Files.readString(filePlath);
    }
}