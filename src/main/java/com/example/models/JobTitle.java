package com.example.models;

/**
 * Class representing a job title with a quality score.
 */
public class JobTitle {
    private final String title;
    private final double score;

    /**
     * Constructs a JobTitle with the specified title and score.
     *
     * @param title the job title
     * @param score the quality score
     */
    public JobTitle(String title, double score) {
        this.title = title;
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public double getScore() {
        return score;
    }
}
