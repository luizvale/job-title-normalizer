package com.example.exeptions;

/**
 * Custom exception class for normalization errors.
 */
public class NormalizationException extends Exception {
    public NormalizationException(String message) {
        super(message);
    }
}
