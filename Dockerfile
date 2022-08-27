# Start with a base image containing Java runtime
FROM adoptopenjdk/openjdk11:latest

# Make port 8080 available to the world outside this container
EXPOSE 8080

VOLUME /var/lib/docker

# The application's jar file
ARG JAR_FILE=build/libs/board-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} board-springboot.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/board-springboot.jar"]
