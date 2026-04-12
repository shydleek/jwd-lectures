package org.epam.jwd.app;

import org.epam.jwd.exception.InvalidInputFileContent;
import org.epam.jwd.exception.PlainNotExist;
import org.epam.jwd.model.Plain;
import org.epam.jwd.model.Point;
import org.epam.jwd.util.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final Point POINT_1 = new Point(1,3,5);
    private static final Point POINT_2 = new Point(1,4,7);
    private static final Point POINT_3 = new Point(-5,2,1);
    private static final Point POINT_4 = new Point(1,3,5);
    private static final Point POINT_5 = new Point(2,6,10);
    private static final Point POINT_6 = new Point(-1,-3,-5);
    private static final int A = 0;
    private static final int B = 0;
    private static final int C = 0;
    private static final int D = 1;

    public static void main(String[] args) throws PlainNotExist, IOException {
        LOG.trace(ApplicationConstants.PROG_START.getValue());
        List<String> savedLines = null;
        try (Stream<String> lines = Files.lines(Path.of(ApplicationConstants.PATH_TO_FILE.getValue()))) {
            savedLines = lines.toList();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        List<List<Double>> result = null;
        try {
            result = savedLines.stream()
                    .map(s -> Arrays.stream(s.split(" "))
                            .map(String::trim)
                            .filter(str -> !str.isEmpty())
                            .flatMap(str -> {
                                try {
                                    return Stream.of(Double.parseDouble(str));
                                } catch (NumberFormatException e) {
                                    LOG.error(e.getMessage(), e);
                                    return Stream.empty();
                                }
                            })
                            .collect(Collectors.toList()))
                    .filter(subList -> subList.size() == 4)
                    .collect(Collectors.toList());
        } catch (InvalidInputFileContent e) {
            LOG.error(e.getMessage(), e);
        }


        LOG.info(result.toString());
//        Plain plain = null;
//        try {
//            plain = new Plain(POINT_1, POINT_2, POINT_3);
//        } catch (PlainNotExist e) {
//            LOG.error(e.getMessage(), e);
//        }
//        if (plain == null) {
//            LOG.error(ApplicationConstants.ERR_PLAIN.getValue());
//        } else {
//            LOG.info(plain.toString());
//        }
//

//        final double a = result.getFirst().getFirst();
//        final double b = result.getFirst().get(1);
//        final double c = result.getFirst().get(2);
//        final double d = result.getFirst().get(3);
//        Plain plain1 = null;
//        try {
//            plain1 = new Plain(a, b, c, d);
//        } catch (PlainNotExist e) {
//            LOG.error(e.getMessage(), e);
//        }
//        if (plain1 == null) {
//            LOG.error(ApplicationConstants.ERR_PLAIN.getValue());
//        } else {
//            LOG.info(plain1.toString());
//        }
//
//        Plain plain2 = null;
//        try {
//            plain2 = new Plain(POINT_4, POINT_5, POINT_6);
//        } catch (PlainNotExist e) {
//            LOG.error(e.getMessage(), e);
//        }
//        if (plain2 == null) {
//            LOG.error(ApplicationConstants.ERR_PLAIN.getValue());
//        } else {
//            LOG.info(plain2.toString());
//        }

        LOG.trace(ApplicationConstants.PROG_END.getValue());
    }
}