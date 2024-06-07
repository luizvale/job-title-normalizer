package com.example.normalizers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.example.loaders.JobTitleLoader;
import com.example.models.JobTitle;
import com.example.exeptions.NormalizationException;
import com.example.strategies.QualityScoreStrategy;

/**
 * Class for normalizing job titles based on a predefined list of normalized job titles.
 */
public class JobTitleNormalizer extends BaseNormalizer {
    private static final Logger logger = Logger.getLogger(JobTitleNormalizer.class.getName());
    private final QualityScoreStrategy qualityScoreStrategy;

    public JobTitleNormalizer(List<String> normalizedJobTitles, QualityScoreStrategy qualityScoreStrategy) {
        super(normalizedJobTitles);
        this.qualityScoreStrategy = qualityScoreStrategy;
    }

    public JobTitleNormalizer(String jobTitlesFilePath, QualityScoreStrategy qualityScoreStrategy) throws IOException {
        this(JobTitleLoader.loadJobTitles(jobTitlesFilePath), qualityScoreStrategy);
    }

    @Override
    public String normalize(String inputTitle) throws NormalizationException {
        validateInputTitle(inputTitle);
        JobTitle bestMatch = findBestMatch(inputTitle);
        String result = bestMatch != null ? bestMatch.getTitle() : "";
        logNormalizationResult(inputTitle, result);
        return result;
    }

    private void validateInputTitle(String inputTitle) throws NormalizationException {
        if (inputTitle == null || inputTitle.isEmpty()) {
            throw new NormalizationException("Input title cannot be null or empty");
        }
    }

    private JobTitle findBestMatch(String inputTitle) {
        JobTitle bestMatch = null;
        for (String normalizedTitle : normalizedJobTitles) {
            double score = qualityScoreStrategy.calculateQualityScore(inputTitle, normalizedTitle);
            if (bestMatch == null || score > bestMatch.getScore()) {
                bestMatch = new JobTitle(normalizedTitle, score);
            }
        }
        return bestMatch;
    }

    private void logNormalizationResult(String inputTitle, String result) {
        logger.info("Normalized '" + inputTitle + "' to '" + result + "'");
    }

    @Override
    protected double calculateQualityScore(String inputTitle, String normalizedTitle) {
        return qualityScoreStrategy.calculateQualityScore(inputTitle, normalizedTitle);
    }
}
