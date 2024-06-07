package com.example.normalizers;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.example.exceptions.NormalizationException;
import com.example.strategies.QualityScoreStrategy;
import com.example.strategies.SimpleQualityScoreStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the JobTitleNormalizer class.
 */
public class JobTitleNormalizerTest {

    private final QualityScoreStrategy strategy = new SimpleQualityScoreStrategy();

    @Test
    public void testNormalize() throws NormalizationException {
        List<String> normalizedJobTitles = List.of(
                "Architect",
                "Software engineer",
                "Quantity surveyor",
                "Accountant"
        );

        JobTitleNormalizer normalizer = new JobTitleNormalizer(normalizedJobTitles, strategy);

        assertEquals("Software engineer", normalizer.normalize("Java engineer"));
        assertEquals("Software engineer", normalizer.normalize("C# engineer"));
        assertEquals("Accountant", normalizer.normalize("Accountant"));
        assertEquals("Accountant", normalizer.normalize("Chief Accountant"));
    }

    @Test
    public void testNormalizeWithInvalidInput() {
        List<String> normalizedJobTitles = List.of(
                "Architect",
                "Software engineer",
                "Quantity surveyor",
                "Accountant"
        );

        JobTitleNormalizer normalizer = new JobTitleNormalizer(normalizedJobTitles, strategy);

        assertThrows(NormalizationException.class, () -> {
            normalizer.normalize("");
        });

        assertThrows(NormalizationException.class, () -> {
            normalizer.normalize(null);
        });
    }

    @Test
    public void testNormalizeWithNoMatch() throws NormalizationException {
        List<String> normalizedJobTitles = List.of(
                "Architect",
                "Software engineer",
                "Quantity surveyor",
                "Accountant"
        );

        JobTitleNormalizer normalizer = new JobTitleNormalizer(normalizedJobTitles, strategy);

        assertEquals("", normalizer.normalize("Unrelated job title"));
    }

    @Test
    public void testNormalizeWithFile() throws IOException, NormalizationException {
        Path tempFile = Files.createTempFile("jobtitles", ".txt");
        Files.write(tempFile, List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant"));

        JobTitleNormalizer normalizer = new JobTitleNormalizer(tempFile.toString(), strategy);

        assertEquals("Software engineer", normalizer.normalize("Java engineer"));
        assertEquals("Software engineer", normalizer.normalize("C# engineer"));
        assertEquals("Accountant", normalizer.normalize("Accountant"));
        assertEquals("Accountant", normalizer.normalize("Chief Accountant"));

        Files.delete(tempFile);
    }
}
