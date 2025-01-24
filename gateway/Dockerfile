# Use the Eclipse Temurin image for Java applications
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file created by Maven
COPY target/gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose the port used by the gateway application
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
