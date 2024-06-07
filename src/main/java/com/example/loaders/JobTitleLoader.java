package com.example.loaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class for loading job titles from an external file.
 */
public class JobTitleLoader {

    // Private constructor to prevent instantiation
    private JobTitleLoader() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Loads job titles from a specified file.
     *
     * @param filePath the path to the file containing job titles
     * @return a list of job titles
     * @throws IOException if an I/O error occurs reading from the file
     */
    public static List<String> loadJobTitles(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .collect(Collectors.toList());
        }
    }
}
