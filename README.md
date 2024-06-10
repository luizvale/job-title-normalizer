# Job Title Normalizer
This project normalizes job titles using a Java service. We use Docker and Docker Compose to facilitate running the project.

## Requirements:

Docker
Docker Compose

## Services

job-title-normalizer

This service is responsible for normalizing job titles. It will be exposed on port 8080.

## maven-build
This service uses the Maven image to build the project before starting the job-title-normalizer service.

## Modifying the Input List
The job titles for normalization are located in the input-job-titles file in the resources folder. The output will be saved to the normalized-job-titles file, also in the resources folder.