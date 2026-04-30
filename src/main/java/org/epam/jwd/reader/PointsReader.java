package org.epam.jwd.reader;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.validation.PointsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PointsReader {

    private static PointsReader instance;

    private static final Logger LOG = LoggerFactory.getLogger(PointsReader.class);

    private PointsReader() {
    }

    public static PointsReader getInstance() {
        if (instance == null) {
            instance = new PointsReader();
        }
        return instance;
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
}

