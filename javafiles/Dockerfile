# Usando a imagem do OpenJDK 21
FROM openjdk:21-jdk-slim

# Definindo o diretório de trabalho no contêiner
WORKDIR /app

# Copiando o arquivo JAR gerado para dentro do contêiner
COPY target/*.jar app.jar

# Comando para rodar o aplicativo Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]

# Expondo a porta 8080
EXPOSE 8080
