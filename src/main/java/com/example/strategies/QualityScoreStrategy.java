package com.example.strategies;

/**
 * Strategy interface for calculating the quality score between job titles.
 */
public interface QualityScoreStrategy {
    /**
     * Calculates the quality score between the input job title and a normalized job title.
     *
     * @param inputTitle the input job title
     * @param normalizedTitle the normalized job title
     * @return the quality score
     */
    double calculateQualityScore(String inputTitle, String normalizedTitle);
}
