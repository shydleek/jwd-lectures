package org.epam.jwd.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointsValidator {
    private static PointsValidator instance;
    private static final Logger LOG = LoggerFactory.getLogger(PointsValidator.class);

    private PointsValidator() {}

    public static PointsValidator getInstance() {
        if (instance == null) {
            instance = new PointsValidator();
        }
        return instance;
    }
}
