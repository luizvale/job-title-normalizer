package com.example.normalizers;

import java.util.List;
/**
 * Abstract base class providing common functionality for normalizing job titles.
 */
public abstract class BaseNormalizer implements Normalizer {
    protected List<String> normalizedJobTitles;

    /**
     * Constructs a BaseNormalizer with a list of normalized job titles.
     *
     * @param normalizedJobTitles the list of normalized job titles
     */
    protected BaseNormalizer(List<String> normalizedJobTitles) {
        if (normalizedJobTitles == null || normalizedJobTitles.isEmpty()) {
            throw new IllegalArgumentException("Normalized job titles list cannot be null or empty");
        }
        this.normalizedJobTitles = normalizedJobTitles;
    }
    
    /**
     * Calculates the quality score between the input job title and a normalized job title.
     *
     * @param inputTitle the input job title
     * @param normalizedTitle the normalized job title
     * @return the quality score
     */
    protected abstract double calculateQualityScore(String inputTitle, String normalizedTitle);
}
