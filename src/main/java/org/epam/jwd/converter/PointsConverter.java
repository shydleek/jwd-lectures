package org.epam.jwd.converter;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.model.Point3d;
import org.epam.jwd.validation.PointsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PointsConverter {

    private static PointsConverter instance;

    private static final Logger LOG = LoggerFactory.getLogger(PointsConverter.class);

    private final PointsValidator pointsValidator;

    private PointsConverter() {
        this.pointsValidator = PointsValidator.getInstance();
    }

    public static PointsConverter getInstance() {
        if (instance == null) {
            instance = new PointsConverter();
        }
        return instance;
    }

    public List<List<Point3d>> convertListOfPoints(List<String> inputLines) {
        List<List<Point3d>> result = new ArrayList<>();

        if (inputLines == null || inputLines.isEmpty()) {
            return result;
        }

        for (String inputLine : inputLines) {
            try {
                result.add(pointsValidator.convert(inputLine));
            } catch (ValidationException e) {
                LOG.error("Validation error, skipped line \"{}\"", inputLine);
            } catch (ParseException e) {
                LOG.error("Parse error, skipped line \"{}\"", inputLine);
            }
        }

        return result;
    }
}