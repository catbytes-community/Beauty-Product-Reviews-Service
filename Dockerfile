# Use Maven with JDK 21 to build the application
FROM maven:3.9.5-eclipse-temurin-21 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .
# download and cache all dependencies
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use a lightweight JDK 21 runtime to run the application
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port (default Spring Boot port is 8080)
EXPOSE 8081

# Command to run the application
CMD ["java", "-jar", "app.jar"]