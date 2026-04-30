package org.epam.jwd.reader;

import org.epam.jwd.exception.ParseException;
import org.epam.jwd.exception.ValidationException;
import org.epam.jwd.validation.CoefficientsValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CoefficientsReader {

    private static CoefficientsReader instance;

    private static final Logger LOG = LoggerFactory.getLogger(CoefficientsReader.class);

    private CoefficientsReader() {
    }

    public static CoefficientsReader getInstance() {
        if (instance == null) {
            instance = new CoefficientsReader();
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
}
