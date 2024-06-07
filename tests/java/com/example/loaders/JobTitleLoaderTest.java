package com.example.loaders;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the JobTitleLoader class.
 */
public class JobTitleLoaderTest {

    @Test
    public void testLoadJobTitles() throws IOException {
        Path tempFile = Files.createTempFile("jobtitles", ".txt");
        Files.write(tempFile, List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant"));

        List<String> jobTitles = JobTitleLoader.loadJobTitles(tempFile.toString());

        assertEquals(4, jobTitles.size());
        assertEquals("Architect", jobTitles.get(0));
        assertEquals("Software engineer", jobTitles.get(1));
        assertEquals("Quantity surveyor", jobTitles.get(2));
        assertEquals("Accountant", jobTitles.get(3));

        Files.delete(tempFile);
    }

    @Test
    public void testLoadJobTitlesWithEmptyLines() throws IOException {
        Path tempFile = Files.createTempFile("jobtitles", ".txt");
        Files.write(tempFile, List.of("Architect", "", "Software engineer", " ", "Quantity surveyor", "Accountant"));

        List<String> jobTitles = JobTitleLoader.loadJobTitles(tempFile.toString());

        assertEquals(4, jobTitles.size());
        assertEquals("Architect", jobTitles.get(0));
        assertEquals("Software engineer", jobTitles.get(1));
        assertEquals("Quantity surveyor", jobTitles.get(2));
        assertEquals("Accountant", jobTitles.get(3));

        Files.delete(tempFile);
    }

    @Test
    public void testLoadJobTitlesWithInvalidPath() {
        assertThrows(IOException.class, () -> {
            JobTitleLoader.loadJobTitles("invalid/path/to/jobtitles.txt");
        });
    }
}
