package com.example;
import com.example.normalizers.JobTitleNormalizer;
import com.example.strategies.QualityScoreStrategy;
import com.example.strategies.SimpleQualityScoreStrategy;
import com.example.exeptions.NormalizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        String jobTitlesFilePath = "normalized-job-titles.txt"; // Nome do arquivo no diretório resources
        String inputFilePath = "input-job-titles.txt"; // Nome do arquivo no diretório resources

        QualityScoreStrategy strategy = new SimpleQualityScoreStrategy();
        JobTitleNormalizer normalizer;

        try {
            List<String> normalizedJobTitles = loadJobTitlesFromResources(jobTitlesFilePath);
            normalizer = new JobTitleNormalizer(normalizedJobTitles, strategy);
        } catch (IOException e) {
            logger.error("Erro ao carregar os títulos normalizados: {}", e.getMessage());
            return;
        }

        try {
            List<String> inputJobTitles = loadJobTitlesFromResources(inputFilePath);
            for (String inputTitle : inputJobTitles) {
                normalizeAndLogTitle(normalizer, inputTitle);
            }
        } catch (IOException e) {
            logger.error("Erro ao carregar os títulos de entrada: {}", e.getMessage());
        }
    }

    private static void normalizeAndLogTitle(JobTitleNormalizer normalizer, String inputTitle) {
        try {
            String normalizedTitle = normalizer.normalize(inputTitle);
            System.out.println("Título original: " + inputTitle);
            System.out.println("Título normalizado: " + normalizedTitle);
        } catch (NormalizationException e) {
            logger.error("Erro ao normalizar o título: {} - {}", inputTitle, e.getMessage());
        }
    }

    private static List<String> loadJobTitlesFromResources(String fileName) throws IOException {
        InputStream inputStream = MainClass.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("Arquivo não encontrado: " + fileName);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.toList());
        }
    }
}
