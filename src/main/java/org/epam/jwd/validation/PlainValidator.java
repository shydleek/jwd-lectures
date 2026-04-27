package org.epam.jwd.validation;

import org.epam.jwd.model.Point3d;
import org.epam.jwd.model.Vector3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class PlainValidator {
    private static PlainValidator instance;
    private static final Logger LOG = LoggerFactory.getLogger(PlainValidator.class);

    private PlainValidator() {}

    public static PlainValidator getInstance() {
        if (instance == null) {
            instance = new PlainValidator();
        }
        return instance;
    }

    public boolean areCoefficientsValidated(BigDecimal a, BigDecimal b, BigDecimal c) {
        return a.compareTo(BigDecimal.ZERO) != 0
                || b.compareTo(BigDecimal.ZERO) != 0
                || c.compareTo(BigDecimal.ZERO) != 0;
    }

    public boolean arePointsValidated(Point3d a, Point3d b, Point3d c) {
        Vector3d n = new Vector3d(a, b).crossProduct(new Vector3d(a, c));
        LOG.info(String.valueOf(n.getX()));
        LOG.info(String.valueOf(n.getY()));
        LOG.info(String.valueOf(n.getZ()));
        return areCoefficientsValidated(n.getX(), n.getY(), n.getZ());
    }
// TODO: isMonic() function that should reduce coeffs
//    public List<BigDecimal> isMonic(List<BigDecimal> list) {
//        if (BigDecimal.ONE.compareTo(list.getFirst()) > 0
//                && BigDecimal.ZERO.compareTo(list.getFirst()) > 0) {
//            BigDecimal k = list.getFirst();
//            list.set(0, BigDecimal.valueOf(1.0));  // a = 1;
//            list.set(1, list.get(1).divide(k, RoundingMode.HALF_UP)); // b /= a;
//            list.set(2, list.get(2).divide(k, RoundingMode.HALF_UP)); // c /= a;
//            list.set(3, list.get(3).divide(k, RoundingMode.HALF_UP)); // d /= a;
//        }
//        return list;
//    }
}
