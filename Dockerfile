
FROM openjdk:17-jdk-slim

# Add a volume to store logs
VOLUME /tmp

# Copy the application JAR file into the container
ARG JAR_FILE=target/grocerybooking-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
