FROM maven:latest AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/simple-java-app-1.0-SNAPSHOT.jar app.jar

ENV ENVIRONMENT=development
CMD ["java", "-jar", "app.jar"]