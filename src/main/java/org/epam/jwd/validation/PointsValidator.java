package org.epam.jwd.validation;

import org.epam.jwd.exception.*;
import org.epam.jwd.model.Point3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PointsValidator {

    private static PointsValidator instance;
    private static final Logger LOG = LoggerFactory.getLogger(PointsValidator.class);
    private static final int EXPECTED_POINTS_COUNT = 9;
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+"); // один и более пробельных символов

    private PointsValidator() {
    }

    public static PointsValidator getInstance() {
        if (instance == null) {
            instance = new PointsValidator();
        }
        return instance;
    }

    public List<Point3d> validate(String line) throws ValidationException, ParseException {
        try {
            validateNotNull(line);
            String trimmed = trimAndValidateNotEmpty(line);
            String[] tokens = splitIntoTokens(trimmed);
            validateTokenCount(tokens);
            return parseTokens(tokens);
        } catch (ValidationException e) {
            throw new ValidationException(e, e.getMessage());
        } catch (ParseException e) {
            throw new ParseException(e, e.getMessage());
        }
    }

    private void validateNotNull(String line) throws NullLineException {
        try {
            if (line == null || line.isEmpty()) {
                throw new NullLineException();
            }
        } catch (NullLineException e) {
            LOG.error("Line should not be null.");
            throw new NullLineException(e, e.getMessage());
        }
    }

    private String trimAndValidateNotEmpty(String line) throws EmptyLineException {
        try {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                throw new EmptyLineException();
            }
            return trimmed;
        } catch (EmptyLineException e) {
            LOG.error("Line should be not empty.");
            throw new EmptyLineException(e, e.getMessage());
        }
    }

    private String[] splitIntoTokens(String trimmed) {
        return WHITESPACE_PATTERN.split(trimmed);
    }

    private void validateTokenCount(String[] tokens) throws InvalidCoefficientsCountException {
        try {
            if (tokens.length != EXPECTED_POINTS_COUNT) {
                throw new InvalidCoefficientsCountException();
            }
        } catch (InvalidCoefficientsCountException e) {
            LOG.error("Number of points in each line should be 9");
            throw new InvalidCoefficientsCountException(e, e.getMessage());
        }
    }

    private List<Point3d> parseTokens(String[] tokens) throws ParseException {
        List<Point3d> values = new ArrayList<>();

        for (int i = 0; i < tokens.length; i += 3) {
            String firstToken = tokens[i];
            String secondToken = tokens[i + 1];
            String thirdToken = tokens[i + 2];

            try {
                LOG.info("[{}, {}, {}]", firstToken, secondToken, thirdToken);

                Point3d point = new Point3d(
                        new BigDecimal(firstToken),
                        new BigDecimal(secondToken),
                        new BigDecimal(thirdToken)
                );
                values.add(point);
            } catch (NumberFormatException e) {
                throw new ParseException(e.getMessage());
            }
        }

        return values;
    }
}
