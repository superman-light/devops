# Étape 1 : Utiliser une image Java de base
FROM openjdk:11-jre-slim

# Étape 2 : Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Étape 3 : Copier le fichier JAR généré dans le conteneur
COPY target/eventsProject-1.0.0.jar app.jar

# Étape 4 : Exposer le port sur lequel l'application écoute
EXPOSE 8080

# Étape 5 : Lancer l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
