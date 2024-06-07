package com.example.strategies;

import java.util.Arrays;

/**
 * Simple implementation of the QualityScoreStrategy using word matching.
 */
public class SimpleQualityScoreStrategy implements QualityScoreStrategy {

    @Override
    public double calculateQualityScore(String inputTitle, String normalizedTitle) {
        String[] inputWords = inputTitle.toLowerCase().split("\\s+");
        String[] normalizedWords = normalizedTitle.toLowerCase().split("\\s+");

        long matchingWords = Arrays.stream(inputWords)
                .filter(word -> Arrays.asList(normalizedWords).contains(word))
                .count();

        return (double) matchingWords / Math.max(inputWords.length, normalizedWords.length);
    }
}
