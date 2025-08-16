# Use a lightweight Java image
FROM eclipse-temurin:17-jdk-alpine

# Set work directory
WORKDIR /app

# Copy the Spring Boot JAR file into the container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port (optional, for local dev clarity)
EXPOSE 8081

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]