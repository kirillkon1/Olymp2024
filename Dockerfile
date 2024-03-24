# Use an official OpenJDK runtime with Java 17 as a parent image
FROM openjdk:19

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR (assuming it's in the target directory after building)
COPY build/libs/*.jar /app/app.jar

# Expose the port that your application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "app.jar"]

LABEL name=olymp-application