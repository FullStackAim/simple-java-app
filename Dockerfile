# Stage 1: Build the application using Maven
FROM maven:latest AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/simple-java-app-1.0-SNAPSHOT.jar app.jar

# Set environment variable
ENV ENVIRONMENT=development

# Run the application
CMD ["java", "-jar", "app.jar"]
