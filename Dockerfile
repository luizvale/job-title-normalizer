# Use a imagem oficial do Maven para construir o projeto
FROM maven:3.6.3-jdk-8 AS build

# Copie o conteúdo do projeto para o contêiner
COPY . /usr/src/app

# Defina o diretório de trabalho
WORKDIR /usr/src/app

# Execute o Maven para construir o projeto e rodar os testes
RUN mvn clean install

# Use uma imagem oficial do OpenJDK para rodar a aplicação
FROM openjdk:8-jre-alpine

# Copie o arquivo JAR do estágio de build para o novo contêiner
COPY --from=build /usr/src/app/target/job-title-normalizer-1.0-SNAPSHOT.jar /app/job-title-normalizer.jar

# Defina o diretório de trabalho
WORKDIR /app

# Comando para rodar a aplicação
CMD ["java", "-jar", "job-title-normalizer.jar"]
