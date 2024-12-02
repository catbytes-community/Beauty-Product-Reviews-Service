# Use the OpenJDK base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/reviews-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port (default Spring Boot port is 8080)
EXPOSE 8081

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]