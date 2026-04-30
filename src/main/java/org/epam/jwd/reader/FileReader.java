package org.epam.jwd.reader;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.validation.CoefficientsValidator;
import org.epam.jwd.validation.PointsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReader {

    private static FileReader instance;

    private static final Logger LOG = LoggerFactory.getLogger(FileReader.class);

    private FileReader() {
    }

    public static FileReader getInstance() {
        if (instance == null) {
            instance = new FileReader();
        }
        return instance;
    }

    public List<List<BigDecimal>> readListOfCoefficients(List<String> inputLines) {
        List<List<BigDecimal>> result = new ArrayList<>();

        if (inputLines == null || inputLines.isEmpty()) {
            return result;
        }

        for (String inputLine : inputLines) {
            try {
                CoefficientsValidator validator = CoefficientsValidator.getInstance();
                result.add(validator.validate(inputLine));
            } catch (ValidationException e) {
                LOG.error("Validation error, skipped line \"{}\"", inputLine);
            } catch (ParseException e) {
                LOG.error("Parse error, skipped line \"{}\"", inputLine);
            }
        }

        return result;
    }

    public List<List<Point3d>> readListOfPoints(List<String> inputLines) {
        List<List<Point3d>> result = new ArrayList<>();

        if (inputLines == null || inputLines.isEmpty()) {
            return result;
        }

        for (String inputLine : inputLines) {
            try {
                PointsValidator validator = PointsValidator.getInstance();
                result.add(validator.validate(inputLine));
            } catch (ValidationException e) {
                LOG.error("Validation error, skipped line \"{}\"", inputLine);
            } catch (ParseException e) {
                LOG.error("Parse error, skipped line \"{}\"", inputLine);
            }
        }

        return result;
    }

    public List<String> readLinesFromFile(Path filePath) throws IOException {
        List<String> linesOfStrings;

        try (Stream<String> lines = Files.lines(filePath)) {
            linesOfStrings = lines.toList();
        } catch (IOException e) {
            LOG.error("File not found, check path");
            throw new IOException(e.getMessage(), e);
        }

        return linesOfStrings;
    }
}
