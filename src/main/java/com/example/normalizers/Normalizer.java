package com.example.normalizers;

import com.example.exeptions.NormalizationException;

/**
 * Interface defining the contract for normalizing job titles.
 */
public interface Normalizer {
    /**
     * Normalizes the given input job title to the closest predefined job title.
     *
     * @param inputTitle the job title to normalize
     * @return the normalized job title
     * @throws NormalizationException if the input title is invalid
     */
    String normalize(String inputTitle) throws NormalizationException;
}
