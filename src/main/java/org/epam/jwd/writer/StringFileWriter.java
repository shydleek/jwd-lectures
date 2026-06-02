package org.epam.jwd.writer;

import java.io.FileWriter;
import java.io.IOException;

public class StringFileWriter {

    private static StringFileWriter instance;

    private StringFileWriter() {
    }

    public static StringFileWriter getInstance() {
        if (instance == null) {
            instance = new StringFileWriter();
        }
        return instance;
    }

    public boolean writeStringToFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(text);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
