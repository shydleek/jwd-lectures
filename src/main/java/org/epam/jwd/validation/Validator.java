package org.epam.jwd.validation;

import org.epam.jwd.exception.InvalidInputFileContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
// TODO: make a filereader and tests for it
public class Validator {
    private static final Logger LOG = LoggerFactory.getLogger(Validator.class);

    public static List<Double> validateCoefficientsFromFile(String path) {
        List<String> savedLines = readLinesFromFile(path);

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
                    .toList();
        } catch (InvalidInputFileContent e) {
            LOG.error(e.getMessage(), e);
        }
        return result.getFirst();
    }
// TODO: VALIDATOR FOR POINT3D CLASS
//    public static List<List<Point3D>> validatePointsFromFile(String path) {
//        List<String> savedLines = null;
//        try (Stream<String> lines = Files.lines(Path.of(path))) {
//            savedLines = lines.toList();
//        } catch (IOException e) {
//            LOG.error(e.getMessage(), e);
//        }
//        List<List<Double>> result = null;
//        try {
//            result = savedLines.stream()
//                    .map(s -> Arrays.stream(s.split(" "))
//                            .map(String::trim)
//                            .filter(str -> !str.isEmpty())
//                            .flatMap(str -> {
//                                try {
//                                    return Stream.of(Double.parseDouble(str));
//                                } catch (NumberFormatException e) {
//                                    LOG.error(e.getMessage(), e);
//                                    return Stream.empty();
//                                }
//                            })
//                            .collect(Collectors.toList()))
//                    .filter(subList -> subList.size() == 3)
//                    .collect(Collectors.toList());
//        } catch (InvalidInputFileContent e) {
//            LOG.error(e.getMessage(), e);
//        }
//        return result;
//    }

    public static List<String> readLinesFromFile(String path){
        List<String> savedLines = null;
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            savedLines = lines.toList();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return savedLines;
    }
}
