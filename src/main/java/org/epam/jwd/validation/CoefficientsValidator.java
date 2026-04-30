package org.epam.jwd.validation;

import org.epam.jwd.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CoefficientsValidator {

    private static CoefficientsValidator instance;
    private static final int EXPECTED_COEFFICIENTS_COUNT = 4;
    private static final Pattern BIGDECIMAL_PATTERN = Pattern.compile(
            "^[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?$"
    );
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+"); // один и более пробельных символов
    private static final Logger LOG = LoggerFactory.getLogger(CoefficientsValidator.class);

    private CoefficientsValidator() {}

    public static CoefficientsValidator getInstance() {
        if (instance == null) {
            instance = new CoefficientsValidator();
        }
        return instance;
    }

    public List<BigDecimal> validate(String line) throws ValidationException, ParseException {
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
            if (tokens.length != EXPECTED_COEFFICIENTS_COUNT) {
                throw new InvalidCoefficientsCountException();
            }
        } catch (InvalidCoefficientsCountException e) {
            LOG.error("Number of coefficients in each line should be 4");
            throw new InvalidCoefficientsCountException(e, e.getMessage());
        }
    }

    private List<BigDecimal> parseTokens(String[] tokens) throws ParseException {
        List<BigDecimal> values = new ArrayList<>();

        for (String token : tokens) {
            try {
                validateTokenFormat(token);
                LOG.info(token);
                BigDecimal value = new BigDecimal(token);
                values.add(value);
            } catch (ParseException e) {
                throw new ParseException(e, e.getMessage());
            }
        }

        return values;
    }

    private void validateTokenFormat(String token) throws InvalidBigDecimalFormatException {
        try {
            if (!BIGDECIMAL_PATTERN.matcher(token).matches()) {
                throw new InvalidBigDecimalFormatException();
            }
        } catch (InvalidBigDecimalFormatException e) {
            LOG.error("Not BigDecimal format in line, should be something like that: 1.245");
            throw new InvalidBigDecimalFormatException(e, e.getMessage());
        }
    }
}
